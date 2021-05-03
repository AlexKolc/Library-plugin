//package com.atlassian.confluence.rest;
//
//import com.atlassian.confluence.model.CommentaryModel;
//import com.atlassian.confluence.service.CommentaryService;
//
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//@Path("/commentary")
//public class CommentaryRest {
//    private final CommentaryService commentaryService;
//
//    @Inject
//    public CommentaryRest(CommentaryService commentaryService) {
//        this.commentaryService = commentaryService;
//    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/addCommentary")
//    public Response addCommentary(CommentaryModel commentaryModel) {
//        commentaryService.addCommentary(commentaryModel);
//        return Response.ok().build();
//    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/deleteCommentary")
//    public Response deleteCommentary(CommentaryModel commentaryModel) {
//        commentaryService.deleteCommentary(commentaryModel.getId());
//        return Response.ok().build();
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/getCommentaries")
//    public CommentaryModel[] getCommentaries(HttpServletRequest request) {
//        return null;
//    }
//}
