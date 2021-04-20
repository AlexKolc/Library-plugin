//package com.atlassian.confluence.service.impl;
//
//import com.atlassian.activeobjects.external.ActiveObjects;
//import com.atlassian.confluence.ao.Author;
//import com.atlassian.confluence.model.AuthorModel;
//import com.atlassian.confluence.service.AuthorService;
//import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
//import com.atlassian.sal.api.user.UserManager;
//import net.java.ao.Query;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//
//
//@Named
//public class AuthorServiceImpl implements AuthorService {
//
//    @ComponentImport
//    private ActiveObjects ao;
//
//    @ComponentImport
//    private UserManager userManager;
//
//    @Inject
//    AuthorServiceImpl(ActiveObjects ao, UserManager userManager) {
//        this.ao = ao;
//        this.userManager = userManager;
//    }
//
//    @Override
//    public void addAuthor(AuthorModel authorModel) {
//        Author[] authors = ao.find(Author.class, Query.select().where("FULL_NAME LIKE ?", authorModel.getFullName()));
//        if (authors == null)
//            return;
//        Author author = ao.create(Author.class);
//        author.setFullName(authorModel.getFullName());
//        author.save();
//    }
//
//    @Override
//    public void deleteAuthor(int id) {
//        Author[] authors = ao.find(Author.class, Query.select().where("AUTHOR_ID LIKE ?", id));
//        for (Author author: authors) {
//            ao.delete(author);
//        }
//    }
//}
