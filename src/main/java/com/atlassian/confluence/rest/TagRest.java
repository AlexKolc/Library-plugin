package com.atlassian.confluence.rest;

import com.atlassian.confluence.model.TagModel;
import com.atlassian.confluence.service.TagService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tag")
public class TagRest {
    private final TagService tagService;

    @Inject
    public TagRest(TagService tagService) {
        this.tagService = tagService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getTags")
    public TagModel[] getTags() {
        return tagService.getTags();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addTag")
    public Response addTag(TagModel tagModel) {
        int status = tagService.addTag(tagModel);
        if (status != 200)
            return Response.status(status).build();
        return Response.ok().build();
    }
}
