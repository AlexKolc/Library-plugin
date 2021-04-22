package com.atlassian.confluence.rest;

import com.atlassian.confluence.model.LendingModel;
import com.atlassian.confluence.service.LendingService;
import javax.servlet.http.HttpServletRequest;

import javax.inject.Inject;
import javax.ws.rs.*;
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
    @Path("/getLendigsByKey")
    public LendingModel[] getLendingsByKey(HttpServletRequest request) {
        // TODO: get user key from http request
        return lendingService.getLendingByKey("");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addToBooked")
    public Response addToBooked(HttpServletRequest request) {
        // TODO: get book id from http request
        lendingService.addLendingBooked(1);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addToWaiting")
    public Response addToWaiting(HttpServletRequest request) {
        // TODO: get book id from http request
        lendingService.addLendingPendingIssue(1);
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
