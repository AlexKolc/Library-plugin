package com.atlassian.confluence.rest;

import com.atlassian.confluence.ao.Tag;
import com.atlassian.confluence.model.EditionTypeModel;
import com.atlassian.confluence.model.TagModel;
import com.atlassian.confluence.service.EditionTypeService;
import com.atlassian.confluence.service.TagService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
