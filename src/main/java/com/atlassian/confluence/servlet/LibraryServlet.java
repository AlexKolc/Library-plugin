package com.atlassian.confluence.servlet;


import com.atlassian.confluence.renderer.template.TemplateRenderer;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.webresource.api.assembler.PageBuilderService;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LibraryServlet extends HttpServlet {
//    @ComponentImport
    private PageBuilderService pageBuilderService;
//    @ComponentImport
    private UserManager userManager;
//    @ComponentImport
//    private final TemplateRenderer templateRenderer;

    @Inject
    LibraryServlet(@ComponentImport PageBuilderService pageBuilderService,
                   @ComponentImport UserManager userManager
                   /*TemplateRenderer templateRenderer*/) {
        this.pageBuilderService = pageBuilderService;
        this.userManager = userManager;
        //this.templateRenderer = templateRenderer;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String webResource;
        if (this.userManager.getRemoteUser() != null) {
            webResource = "Library:library-servlet-resources";
            resp.setContentType("text/html");
            resp.getWriter().write("<html><body>User</body></html>");
        } else {
            webResource = "Library:no-confluence-user";
            resp.setContentType("text/html");
            resp.getWriter().write("<html><body>No User</body></html>");
        }
        this.pageBuilderService
                .assembler()
                .resources()
                .requireWebResource(webResource);
//        templateRenderer.render("Library.vm", null, null);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        resp.setContentType("text/html");
////        resp.getWriter().write("<html><body>Hello World</body></html>");
//    }
}