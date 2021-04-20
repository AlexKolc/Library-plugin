//package com.atlassian.confluence.rest;
//
//import com.atlassian.confluence.ao.Author;
//import com.atlassian.confluence.model.AuthorModel;
//import com.atlassian.confluence.service.AuthorService;
//import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
//
//import javax.inject.Inject;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//@Path("/author")
//public class AuthorRest {
//    private final AuthorService authorService;
//
//    @Inject
//    AuthorRest(@ComponentImport AuthorService authorService) {
//        this.authorService = authorService;
//    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/addAuthor")
//    public Response addAuthor(AuthorModel authorModel) {
//        authorService.addAuthor(authorModel);
//        return Response.ok().build();
//    }
//}
