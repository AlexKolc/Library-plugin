package com.atlassian.confluence.servlet;


import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.auth.LoginUriProvider;
import com.atlassian.sal.api.user.UserKey;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.templaterenderer.TemplateRenderer;

import com.atlassian.webresource.api.assembler.PageBuilderService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;


public class LibraryServlet extends HttpServlet {
//    @ComponentImport
//    private PageBuilderService pageBuilderService;
////    @ComponentImport
//    private UserManager userManager;
////    @ComponentImport
////    private final TemplateRenderer templateRenderer;
//
//    @Inject
//    LibraryServlet(@ComponentImport PageBuilderService pageBuilderService,
//                   @ComponentImport UserManager userManager
//                   /*TemplateRenderer templateRenderer*/) {
//        this.pageBuilderService = pageBuilderService;
//        this.userManager = userManager;
//        //this.templateRenderer = templateRenderer;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String webResource;
//        if (this.userManager.getRemoteUser() != null) {
//            webResource = "Library:library-servlet-resources";
//            resp.setContentType("text/html");
//            resp.getWriter().write("<html><body>User</body></html>");
//        } else {
//            webResource = "Library:no-confluence-user";
//            resp.setContentType("text/html");
//            resp.getWriter().write("<html><body>No User</body></html>");
//        }
//        this.pageBuilderService
//                .assembler()
//                .resources()
//                .requireWebResource(webResource);
////        templateRenderer.render("Library.vm", null, null);
//    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        resp.setContentType("text/html");
////        resp.getWriter().write("<html><body>Hello World</body></html>");
//    }

    @ComponentImport
    private final TemplateRenderer templateRenderer;

    @Inject
    public LibraryServlet(TemplateRenderer templateRenderer) {
        this.templateRenderer = templateRenderer;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        templateRenderer.render("/templates/Main.vm", response.getWriter());
    }
}