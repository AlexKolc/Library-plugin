//package com.atlassian.confluence.service.impl;
//
//import com.atlassian.activeobjects.external.ActiveObjects;
//import com.atlassian.confluence.ao.*;
//import com.atlassian.confluence.ao.BookToLending;
//import com.atlassian.confluence.model.*;
//import com.atlassian.confluence.service.BookService;
//import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
//import com.atlassian.sal.api.user.UserManager;
//import net.java.ao.Query;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//
//@Named
//public class BookServiceImpl implements BookService {
//    @ComponentImport
//    private final ActiveObjects ao;
//
//    @ComponentImport
//    private final UserManager userManager;
//
//    @Inject
//    public BookServiceImpl(ActiveObjects ao, UserManager userManager) {
//        this.ao = ao;
//        this.userManager = userManager;
//    }
//
//    @Override
//    public Book addBook(BookModel bookModel) {
//        final Book book = ao.create(Book.class);
//        book.setName(bookModel.getName());
//        book.setIsnb(bookModel.getIsnb());
//        book.setYearPublishing(bookModel.getYearPublishing());
//        book.setPageVolume(bookModel.getPageVolume());
//        book.setCountCopies(bookModel.getCountCopies());
//        book.setImageUrl(bookModel.getImageUrl());
//
//        return book;
//    }
//
//    @Override
//    public void deleteBook(Integer id) {
//        Book book = ao.get(Book.class, id);
//        if (book == null)
//            return;
//
//        Commentary[] commentaries = ao.find(Commentary.class, Query.select().where("BOOK_ID LIKE ?", id));
//        for (Commentary commentary: commentaries) {
//            ao.delete(commentary);
//        }
//
//        BookToLending[] bookToLendings = ao.find(BookToLending.class, Query.select().where("BOOK LIKE ?", id));
//        for (BookToLending bookToLending: bookToLendings) {
//            Lending[] lendings = ao.find(Lending.class, Query.select().where("LENDING_ID LIKE ?", bookToLending.getID()));
//            ao.delete(lendings[0]);
//            ao.delete(bookToLending);
//        }
//
//        BookToAuthor[] bookToAuthors = ao.find(BookToAuthor.class, Query.select().where("BOOK LIKE ?", id));
//        for (BookToAuthor bookToAuthor: bookToAuthors) {
//            ao.delete(bookToAuthor);
//        }
//
//        BookToTag[] bookToTag = ao.find(BookToTag.class, Query.select().where("BOOK LIKE ?", id));
//        for (BookToAuthor bookToAuthor: bookToAuthors) {
//            ao.delete(bookToTag);
//        }
//
//        BookToEditionType[] bookToEditionTypes = ao.find(BookToEditionType.class, Query.select().where("BOOK LIKE ?", id));
//        for (BookToEditionType bookToEditionType: bookToEditionTypes) {
//            ao.delete(bookToTag);
//        }
//
//        ao.delete(book);
//    }
//
//    @Override
//    public BookModel[] getAllBooks() {
//        Book[] books = ao.find(Book.class);
//        BookModel[] bookModels = new BookModel[books.length];
//        for (int i = 0; i < books.length; i++) {
//            bookModels[i] = new BookModel(books[i]);
//
//        }
//        return bookModels;
//    }
//}
