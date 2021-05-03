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
import java.util.Enumeration;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/lending")
public class LendingRest {
    private static final Logger log = LoggerFactory.getLogger(LendingModel.class);
    private final UserManager userManager;
    private final LendingService lendingService;

    @Inject
    public LendingRest(@ComponentImport UserManager userManager, LendingService lendingService) {
        this.userManager = userManager;
        this.lendingService = lendingService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getLendingsByKey")
    public LendingModel[] getLendingsByKey(/*HttpServletRequest request*/) {
        return lendingService.getLendingByKey();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getLendings")
    public LendingModel[] getLendings() {
        return lendingService.getLendings();
    }

    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/changeStatus")
    public Response changeStatus(@Context HttpServletRequest request) {
        String status = request.getParameter("status");
        int lendingId = Integer.parseInt(request.getParameter("lending_id"));
        lendingService.changeStatus(lendingId, status);
        return Response.ok().build();
    }

    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addToBooked")
    public Response addToBooked(@Context HttpServletRequest request) {
        int bookId = Integer.parseInt(request.getParameter("book_id"));
        lendingService.addLendingBooked(bookId);
        return Response.ok().build();
    }

    @POST
    //@Consumes(MediaType.APPLICATION_JSON)
    @Path("/addToWaiting")
    public Response addToWaiting(@Context HttpServletRequest request) {
        int bookId = Integer.parseInt(request.getParameter("book_id"));
        lendingService.addLendingPendingIssue(bookId);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deleteLending")
    public Response deleteLending(HttpServletRequest request) {
        // TODO: get lending id from http request
        lendingService.deleteLending(1);
        return Response.ok().build();
    }
}
