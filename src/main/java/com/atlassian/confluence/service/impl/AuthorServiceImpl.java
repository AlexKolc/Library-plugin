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
//    @Inject
//    AuthorServiceImpl(ActiveObjects ao) {
//        this.ao = ao;
//    }
//
//    @Override
//    public AuthorModel[] getAuthors() {
//        return new AuthorModel[0];
//    }
//}
