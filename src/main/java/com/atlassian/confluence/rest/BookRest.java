package com.atlassian.confluence.rest;

import com.atlassian.confluence.model.BookModel;
import com.atlassian.confluence.service.BookService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/book")
public class BookRest {
    private final BookService bookService;

    @Inject
    public BookRest(BookService bookService) {
        this.bookService = bookService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addBook")
    public Response addBook(BookModel bookModel) {
        bookService.addBook(bookModel);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getBooks")
    public BookModel[] getBooks() {
        return bookService.getAllBooks();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getBookById")
    public BookModel getBookById(@Context HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        return bookService.getBookById(id);
    }

}
