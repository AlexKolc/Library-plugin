package com.atlassian.confluence.rest;

import com.atlassian.confluence.model.EditionTypeModel;
import com.atlassian.confluence.service.EditionTypeService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/edition")
public class EditionTypeRest {
    private final EditionTypeService editionTypeService;

    @Inject
    public EditionTypeRest(EditionTypeService editionTypeService) {
        this.editionTypeService = editionTypeService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getEditions")
    public EditionTypeModel[] getEditionTypes() {
        return editionTypeService.getEditionTypes();
    }
}
