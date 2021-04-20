//package com.atlassian.confluence.rest;
//
//import com.atlassian.confluence.model.BookModel;
//import com.atlassian.confluence.service.BookService;
//
//import javax.inject.Inject;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//@Path("/book")
//public class BookRest {
//    private final BookService bookService;
//
//    @Inject
//    public BookRest(BookService bookService) {
//        this.bookService = bookService;
//    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/addBook")
//    public Response addBook(BookModel bookModel) {
//        bookService.addBook(bookModel);
//        return Response.ok().build();
//    }
//}
