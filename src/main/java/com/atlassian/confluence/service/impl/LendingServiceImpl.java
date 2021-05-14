package com.atlassian.confluence.service.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.confluence.ao.Book;
import com.atlassian.confluence.ao.Lending;
import com.atlassian.confluence.mail.template.ConfluenceMailQueueItem;
import com.atlassian.confluence.model.LendingModel;
import com.atlassian.confluence.service.LendingService;
import com.atlassian.mail.Email;
import com.atlassian.mail.MailFactory;
import com.atlassian.mail.server.SMTPMailServer;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.user.UserManager;
import com.sun.mail.imap.IMAPProvider;
import com.sun.mail.pop3.POP3Provider;
import com.sun.mail.smtp.SMTPProvider;
import net.java.ao.Query;
import com.atlassian.core.task.MultiQueueTaskManager;
import com.atlassian.mail.queue.MailQueueItem;
import static com.atlassian.confluence.mail.template.ConfluenceMailQueueItem.MIME_TYPE_HTML;
import org.apache.regexp.RE;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.Objects;

@Named
public class LendingServiceImpl implements LendingService {
    private final String BOOKED = "Забронировано";
    private final String WAITING = "Ожидается выдача";
    private final String ON_HANDS = "На руках";
    private final String RETURNED = "Возвращена";
    private final String LOST = "Потеряна";

    @ComponentImport
    private final ActiveObjects ao;

    @ComponentImport
    private final UserManager userManager;

    @Inject
    public LendingServiceImpl(ActiveObjects ao, UserManager userManager) {
        this.ao = ao;
        this.userManager = userManager;
    }

    @Override
    public LendingModel[] getLendings() {
        Lending[] lendings = ao.find(Lending.class);
        LendingModel[] lendingModels = new LendingModel[lendings.length];
        for (int i = 0; i < lendings.length; i++) {
            lendingModels[i] = new LendingModel(lendings[i]);
        }
        return lendingModels;
    }

    @Override
    public LendingModel[] getLendingByKey() {
        String userKey = Objects.requireNonNull(userManager.getRemoteUserKey()).getStringValue();
        Lending[] lendings = ao.find(Lending.class, Query.select().where("USER_KEY LIKE ?", userKey).order("ID DESC"));
        LendingModel[] lendingModels = new LendingModel[lendings.length];
        for (int i = 0; i < lendings.length; i++) {
            lendingModels[i] = new LendingModel(lendings[i]);
        }
        return lendingModels;
    }

    @Override
    public int addLendingBooked(int bookId) {
        Lending[] findLending = ao.find(Lending.class, Query.select().where(
                "BOOK_ID LIKE ? AND USER_KEY LIKE ? AND STATUS LIKE ?",
                bookId, Objects.requireNonNull(userManager.getRemoteUserKey()).getStringValue(), BOOKED));

        if (findLending.length != 0) {
            return 208;
        }
        Lending lending = ao.create(Lending.class);
        lending.setStatus(BOOKED);
        lending.setDateChangedStatus(new Date());
        lending.setUserKey(Objects.requireNonNull(userManager.getRemoteUserKey()).getStringValue());
        lending.setUserName(Objects.requireNonNull(userManager.getRemoteUser()).getFullName());
        lending.setUserEmail(Objects.requireNonNull(userManager.getRemoteUser()).getEmail());
        lending.setBook(ao.find(Book.class, Query.select().where("ID LIKE ?", bookId))[0]);
        lending.save();
        return 200;
    }

    @Override
    public int addLendingPendingIssue(int bookId) {
        Lending[] findLending = ao.find(Lending.class, Query.select().where(
                "BOOK_ID LIKE ? AND USER_KEY LIKE ? AND (STATUS LIKE ? OR STATUS LIKE ? OR STATUS LIKE ?)",
                bookId, Objects.requireNonNull(userManager.getRemoteUserKey()).getStringValue(), BOOKED, WAITING, ON_HANDS));
        if (findLending.length != 0) {
            return 208;
        }
        Book book = ao.find(Book.class, Query.select().where("ID LIKE ?", bookId))[0];
        if (book.getCountCopies() == 0) {
            return 500;
        }
        book.setCountCopies(book.getCountCopies() - 1);
        book.save();
        Lending lending = ao.create(Lending.class);
        lending.setBook(book);
        lending.setStatus(WAITING);
        lending.setDateChangedStatus(new Date());
        lending.setUserKey(Objects.requireNonNull(userManager.getRemoteUserKey()).getStringValue());
        lending.setUserName(Objects.requireNonNull(userManager.getRemoteUser()).getFullName());
        lending.setUserEmail(Objects.requireNonNull(userManager.getRemoteUser()).getEmail());
        lending.save();
        return 200;
    }

    @Override
    public LendingModel changeStatus(int lendingId, String status) {
        Lending lending = ao.find(Lending.class, Query.select().where("ID LIKE ?", lendingId))[0];

        lending.setDateChangedStatus(new Date());
        if (status.equals("ON_HANDS")) {
            lending.setStatus(ON_HANDS);
            lending.setDateOfIssue(new Date());
        }

        if (status.equals("RETURNED")) {
            lending.setStatus(RETURNED);
            lending.setReturnedDate(new Date());
            Book book = lending.getBook();
            if (!haveBooked(book.getID())) {
                book.setCountCopies(book.getCountCopies() + 1);
                book.save();
            }
        }

        if (status.equals("LOST")) {
            lending.setStatus(LOST);
            lending.setReturnedDate(new Date());
            lending.setIsLost(true);
        }

        lending.save();
        return new LendingModel(lending);
    }

    private boolean haveBooked(int bookId) {
        Lending[] lendings = ao.find(Lending.class, Query.select().where("BOOK_ID LIKE ? AND STATUS LIKE ?", bookId, BOOKED).order("DATE_CHANGED_STATUS"));
        if (lendings.length == 0)
            return false;
        Lending lending = lendings[0];
        lending.setStatus(WAITING);
        lending.setDateChangedStatus(new Date());
        lending.save();

        createMessage(lending.getUserName(), lending.getUserEmail(), lending.getBook().getName());
        return true;
    }

    public void sendEmail(String body, String subject, String emailAddress)  {
        SMTPMailServer mailServer = MailFactory.getServerManager().getDefaultSMTPMailServer();
        Email email = new Email(emailAddress);
        email.setSubject(subject);
        email.setMimeType("text/html");
        email.setBody(body);
        try {
            if (mailServer != null) {
                mailServer.getSession().addProvider(new IMAPProvider());
                mailServer.getSession().addProvider(new POP3Provider());
                mailServer.getSession().addProvider(new SMTPProvider());
                mailServer.send(email);
            }
        }
        catch (Exception e){
        }
    }

    private void createMessage(String userName, String userEmail, String bookName) {
        String subjectEmail = "Библиотека BIA";
        String bodyMessage = "Здравствуйте!<br>"
                + "Уведомляем Вас о том, что появился свободный экземпляр книги \""
                + bookName + "\".<br>"
                + "Книга ожидает вас в библиотеке." + "<br><br>"
                + "---<br>"
                + "С уважением,<br>"
                + "Библиотека BIA";
        sendEmail(bodyMessage, subjectEmail, userEmail);
    }

    @Override
    public void deleteLending(int id) {
        Lending lending = ao.find(Lending.class, Query.select().where("ID LIKE ?", id))[0];
        if (lending.getStatus().equals("Ожидается выдача")) {
            Book book = lending.getBook();
            book.setCountCopies(book.getCountCopies() + 1);
            book.save();
        }
        ao.delete(lending);
    }

    @Override
    public void checkWaitingBooks() {
        int maxDaysInWaiting = 5;
        Date nowDate = new Date();
        Lending[] lendings = ao.find(Lending.class, Query.select().where("STATUS LIKE ?", WAITING));
        for (int i = 0; i < lendings.length; i++) {
            Date dateStatus = lendings[i].getDateChangedStatus();
            int delta = (int) (nowDate.getTime() - dateStatus.getTime()) / (24 * 60 * 60 * 1000);
            if (delta > maxDaysInWaiting) {
                Book book = lendings[i].getBook();
                if (!haveBooked(book.getID())) {
                    book.setCountCopies(book.getCountCopies() + 1);
                    book.save();
                }
                ao.delete(lendings[i]);
            }
        }
    }
}
