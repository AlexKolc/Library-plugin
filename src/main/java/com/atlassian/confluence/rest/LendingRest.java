package com.atlassian.confluence.rest;

import com.atlassian.confluence.model.BookModel;
import com.atlassian.confluence.model.LendingModel;
import com.atlassian.confluence.service.CommentaryService;
import com.atlassian.confluence.service.LendingService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.github.kristofa.brave.http.HttpServerRequest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/lending")
public class LendingRest {
    private final LendingService lendingService;

    @Inject
    public LendingRest(@ComponentImport LendingService lendingService) {
        this.lendingService = lendingService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getLendigsByKey")
    public LendingModel[] getLendingsByKey(HttpServerRequest request) {
        // TODO: get user key from http request
        return lendingService.getLendingByKey("");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addToBooked")
    public Response addToBooked(HttpServerRequest request) {
        // TODO: get book id from http request
        lendingService.addLendingBooked(1);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addToWaiting")
    public Response addToWaiting(HttpServerRequest request) {
        // TODO: get book id from http request
        lendingService.addLendingPendingIssue(1);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deleteLending")
    public Response deleteLending(HttpServerRequest request) {
        // TODO: get lending id from http request
        lendingService.deleteLending(1);
        return Response.ok().build();
    }
}
