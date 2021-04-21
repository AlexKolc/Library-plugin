//package com.atlassian.confluence.rest;
//
//import com.atlassian.confluence.ao.Author;
//import com.atlassian.confluence.model.AuthorModel;
//import com.atlassian.confluence.service.AuthorService;
//import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
//
//import javax.inject.Inject;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//@Path("/author")
//public class AuthorRest {
//    private final AuthorService authorService;
//
//    @Inject
//    AuthorRest(AuthorService authorService) {
//        this.authorService = authorService;
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/getAuthors")
//    public Response addAuthor(AuthorModel authorModel) {
//        authorService.getAuthors();
//        return Response.ok().build();
//    }
//}
