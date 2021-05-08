package com.atlassian.confluence.rest;

import com.atlassian.confluence.model.CommentaryModel;
import com.atlassian.confluence.service.CommentaryService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/commentary")
public class CommentaryRest {
    private final CommentaryService commentaryService;

    @Inject
    public CommentaryRest(CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addCommentary")
    public CommentaryModel addCommentary(CommentaryModel commentaryModel) {
        return commentaryService.addCommentary(commentaryModel);
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/deleteCommentary")
//    public Response deleteCommentary(HttpServletRequest request) {
//        commentaryService.deleteCommentary(commentaryModel.getId());
//        return Response.ok().build();
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getCommentaries")
    public CommentaryModel[] getCommentaries(@Context HttpServletRequest request) {
        int bookId = Integer.parseInt(request.getParameter("book_id"));
        return commentaryService.getCommentaries(bookId);
    }
}
