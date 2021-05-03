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

        String[] authorsStr = bookModel.getAuthors().replaceAll(",\\s+", ",").split(",");
        for (String authorStr : authorsStr) {
            final Author[] authors = ao.find(Author.class, Query.select().where("FULL_NAME LIKE ?", authorStr));
            final Author author;
            if (authors.length == 0) {
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

//        String[] tagsStr = bookModel.getTags().replaceAll(",\\s+", ",").split(",");
//        for (String tagStr : tagsStr) {
//            final Tag[] tags = ao.find(Tag.class, Query.select().where("NAME LIKE ?", tagStr));
//            final Tag tag;
//            if (tags.length == 0) {
//                tag = ao.create(Tag.class);
//                tag.setName(tagStr);
//                tag.save();
//            } else {
//                tag = tags[0];
//            }
//
//            final BookToTag bookToTag = ao.create(BookToTag.class);
//            bookToTag.setBook(book);
//            bookToTag.setTag(tag);
//            bookToTag.save();
//        }

        String[] editionTypesStr = bookModel.getEditionTypes().replaceAll(",\\s+", ",").split(",");
        for (String editionTypeStr : editionTypesStr) {
            final EditionType[] editionTypes = ao.find(EditionType.class, Query.select().where("TYPE_NAME LIKE ?", editionTypeStr));
            final EditionType editionType;
            if (editionTypes.length == 0) {
                editionType = ao.create(EditionType.class);
                editionType.setTypeName(editionTypeStr);
                editionType.save();
            } else {
                editionType = editionTypes[0];
            }

            final BookToEditionType bookToEditionType = ao.create(BookToEditionType.class);
            bookToEditionType.setBook(book);
            bookToEditionType.setEditionType(editionType);
            bookToEditionType.save();
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
            StringBuilder authorsStr = new StringBuilder();
            Author[] authors = books[i].getAuthors();
            for (int j = 0; j < authors.length; j++) {
                authorsStr.append(authors[j].getFullName());
                if (j != authors.length - 1)
                    authorsStr.append(", ");
            }
            bookModels[i].setAuthors(authorsStr.toString());

            StringBuilder tagsStr = new StringBuilder();
            Tag[] tags = books[i].getTags();
            for (int j = 0; j < tags.length; j++) {
                tagsStr.append(tags[j].getName());
                if (j != tags.length - 1)
                    tagsStr.append(", ");
            }
            bookModels[i].setTags(tagsStr.toString());

            StringBuilder editionStr = new StringBuilder();
            EditionType[] editionTypes = books[i].getEditionTypes();
            for (int j = 0; j < editionTypes.length; j++) {
                editionStr.append(editionTypes[j].getTypeName());
                if (j != tags.length - 1)
                    editionStr.append(", ");
            }
            bookModels[i].setEditionTypes(editionStr.toString());
        }
        return bookModels;
    }
}
