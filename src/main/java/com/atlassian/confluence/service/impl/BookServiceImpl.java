package com.atlassian.confluence.service.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.confluence.ao.*;
import com.atlassian.confluence.model.*;
import com.atlassian.confluence.service.BookService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.user.UserManager;
import net.java.ao.Query;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class BookServiceImpl implements BookService {
    @ComponentImport
    private final ActiveObjects ao;

    @ComponentImport
    private final UserManager userManager;

    @Inject
    public BookServiceImpl(ActiveObjects ao, UserManager userManager) {
        this.ao = ao;
        this.userManager = userManager;
    }

    @Override
    public Book addBook(BookModel bookModel) {
        final Book book = ao.create(Book.class);
        book.setName(bookModel.getName());
        book.setIsnb(bookModel.getIsnb());
        book.setYearPublishing(bookModel.getYearPublishing());
        book.setPageVolume(bookModel.getPageVolume());
        book.setCountCopies(bookModel.getCountCopies());
        book.setImageUrl(bookModel.getImageUrl());
        book.setPublisher(bookModel.getPublisher());
        book.setDescription(bookModel.getDescription());

        book.save();

        String[] authorsStr = bookModel.getAuthors().replaceAll("\\s+", "").split(",");
        for (String authorStr : authorsStr) {
            final Author[] authors = ao.find(Author.class, Query.select().where("FULL_NAME LIKE ?", authorStr));
            final Author author;
            if (authors == null) {
                author = ao.create(Author.class);
                author.setFullName(authorStr);
                author.save();
            } else {
                author = authors[0];
            }

            final BookToAuthor bookToAuthor = ao.create(BookToAuthor.class);
            bookToAuthor.setBook(book);
            bookToAuthor.setAuthor(author);
            bookToAuthor.save();
        }


        return book;
    }

    @Override
    public void deleteBook(Integer id) {
        Book book = ao.get(Book.class, id);
        if (book == null)
            return;

        Commentary[] commentaries = ao.find(Commentary.class, Query.select().where("BOOK_ID LIKE ?", id));
        for (Commentary commentary : commentaries) {
            ao.delete(commentary);
        }

        Lending[] lendings = ao.find(Lending.class, Query.select().where("BOOK_ID LIKE ?", id));
        for (Lending lending : lendings) {
            ao.delete(lending);
        }

        BookToAuthor[] bookToAuthors = ao.find(BookToAuthor.class, Query.select().where("BOOK LIKE ?", id));
        for (BookToAuthor bookToAuthor : bookToAuthors) {
            ao.delete(bookToAuthor);
        }

        BookToTag[] bookToTag = ao.find(BookToTag.class, Query.select().where("BOOK LIKE ?", id));
        for (BookToAuthor bookToAuthor : bookToAuthors) {
            ao.delete(bookToTag);
        }

        BookToEditionType[] bookToEditionTypes = ao.find(BookToEditionType.class, Query.select().where("BOOK LIKE ?", id));
        for (BookToEditionType bookToEditionType : bookToEditionTypes) {
            ao.delete(bookToTag);
        }

        ao.delete(book);
    }

    @Override
    public BookModel[] getAllBooks() {
        Book[] books = ao.find(Book.class);
        BookModel[] bookModels = new BookModel[books.length];
        for (int i = 0; i < books.length; i++) {
            bookModels[i] = new BookModel(books[i]);
            BookToAuthor[] bookToAuthors = ao.find(BookToAuthor.class, Query.select().where("BOOK_ID LIKE ?", bookModels[i].getId()));
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < bookToAuthors.length; j++) {
                Author[] author = ao.find(Author.class, Query.select().where("ID LIKE ?", bookToAuthors[j].getAuthor()));
                if (author != null)
                    str.append(author[0].getFullName());
                if (j != bookToAuthors.length - 1)
                    str.append(", ");
            }
            bookModels[i].setAuthors(str.toString());
        }
        return bookModels;
    }
}
