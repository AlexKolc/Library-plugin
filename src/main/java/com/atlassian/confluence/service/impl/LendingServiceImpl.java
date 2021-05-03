package com.atlassian.confluence.service.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.confluence.ao.Book;
import com.atlassian.confluence.ao.Lending;
import com.atlassian.confluence.model.LendingModel;
import com.atlassian.confluence.service.LendingService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.user.UserManager;
import net.java.ao.Query;
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
    private final String RETURNED = "Возвращено";
    private final String LOST = "Потеряно";

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
    public void addLendingBooked(int bookId) {
        Lending lending = ao.create(Lending.class);
        lending.setStatus(BOOKED);
        lending.setDateChangedStatus(new Date());
        lending.setUserKey(Objects.requireNonNull(userManager.getRemoteUserKey()).getStringValue());
        lending.setUserName(Objects.requireNonNull(userManager.getRemoteUser()).getFullName());
        lending.setUserEmail(Objects.requireNonNull(userManager.getRemoteUser()).getEmail());
        lending.setBook(ao.find(Book.class, Query.select().where("ID LIKE ?", bookId))[0]);
        lending.save();
    }

    @Override
    public void addLendingPendingIssue(int bookId) {
        Lending lending = ao.create(Lending.class);
        lending.setStatus(WAITING);
        lending.setDateChangedStatus(new Date());
        lending.setUserKey(Objects.requireNonNull(userManager.getRemoteUserKey()).getStringValue());
        lending.setUserName(Objects.requireNonNull(userManager.getRemoteUser()).getFullName());
        lending.setUserEmail(Objects.requireNonNull(userManager.getRemoteUser()).getEmail());
        Book book = ao.find(Book.class, Query.select().where("ID LIKE ?", bookId))[0];
        book.setCountCopies(book.getCountCopies() - 1);
        book.save();
        lending.setBook(book);
        lending.save();
    }

    @Override
    public void changeStatus(int lendingId, String status) {
        Lending lending = ao.find(Lending.class, Query.select().where("ID LIKE ?", lendingId))[0];

        lending.setDateChangedStatus(new Date());
        switch (status) {
            case "ON_HANDS":
                lending.setStatus(ON_HANDS);
                lending.setDateOfIssue(new Date());
                break;
            case "RETURNED":
                lending.setStatus(RETURNED);
                lending.setReturnedDate(new Date());
                Book book = lending.getBook();
                if (!haveBooked(book.getID())) {
                    book.setCountCopies(book.getCountCopies() + 1);
                    book.save();
                }
                break;
            case "LOST":
                lending.setStatus(LOST);
                lending.setReturnedDate(new Date());
                lending.setIsLost(true);
                break;
        }
        lending.save();
    }

    private boolean haveBooked(int bookId) {
        Lending[] lendings = ao.find(Lending.class, Query.select().where("BOOK_ID LIKE ? AND STATUS LIKE ?", bookId, BOOKED).order("DATE_CHANGED_STATUS"));
        if (lendings.length == 0)
            return false;
        Lending lending = lendings[0];
        lending.setStatus(WAITING);
        lending.setDateChangedStatus(new Date());
        lending.save();
        return true;
    }

    @Override
    public void deleteLending(int id) {
        Lending[] lendings = ao.find(Lending.class, Query.select().where("ID LIKE ?", id));
        ao.delete(lendings[0]);
    }
}
