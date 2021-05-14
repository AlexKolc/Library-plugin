package com.atlassian.confluence.rest;

import com.atlassian.confluence.model.LendingModel;
import com.atlassian.confluence.service.LendingService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.user.UserManager;

import javax.servlet.http.HttpServletRequest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/lending")
public class LendingRest {
    private final LendingService lendingService;

    @Inject
    public LendingRest(LendingService lendingService) {
        this.lendingService = lendingService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getLendingsByKey")
    public LendingModel[] getLendingsByKey() {
        return lendingService.getLendingByKey();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getLendings")
    public LendingModel[] getLendings() {
        return lendingService.getLendings();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/changeStatus")
    public LendingModel changeStatus(@Context HttpServletRequest request) {
        String status = request.getParameter("status");
        int lendingId = Integer.parseInt(request.getParameter("lending_id"));
        return lendingService.changeStatus(lendingId, status);
    }

    @GET
//    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addToBooked")
        public Response addToBooked(@Context HttpServletRequest request) {
        int bookId = Integer.parseInt(request.getParameter("book_id"));
        int status = lendingService.addLendingBooked(bookId);
        if (status == 200)
            return Response.ok().build();
        return Response.status(status).build();
    }

    @GET
    //@Consumes(MediaType.APPLICATION_JSON)
    @Path("/addToWaiting")
    public Response addToWaiting(@Context HttpServletRequest request) {
        int bookId = Integer.parseInt(request.getParameter("book_id"));
        int status = lendingService.addLendingPendingIssue(bookId);
        if (status == 200)
            return Response.ok().build();
        return Response.status(status).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deleteLending")
    public Response deleteLending(LendingModel lendingModel) {
        lendingService.deleteLending(lendingModel.getId());
        return Response.ok().build();
    }
}
