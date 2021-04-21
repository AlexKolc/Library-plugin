package com.atlassian.confluence.service.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.confluence.ao.Author;
import com.atlassian.confluence.ao.BookToAuthor;
import com.atlassian.confluence.ao.EditionType;
import com.atlassian.confluence.model.BookModel;
import com.atlassian.confluence.model.EditionTypeModel;
import com.atlassian.confluence.service.EditionTypeService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.user.UserManager;
import net.java.ao.Query;

import javax.inject.Inject;
import javax.inject.Named;


@Named
public class EditionTypeServiceImpl implements EditionTypeService {
    @ComponentImport
    private final ActiveObjects ao;

    @ComponentImport
    private final UserManager userManager;

    @Inject
    public EditionTypeServiceImpl(ActiveObjects ao, UserManager userManager) {
        this.ao = ao;
        this.userManager = userManager;
    }

    @Override
    public EditionTypeModel[] getEditionTypes() {
        EditionType[] editionTypes = ao.find(EditionType.class);
        EditionTypeModel[] editionTypeModels = new EditionTypeModel[editionTypes.length];
        for (int i = 0; i < editionTypes.length; i++) {
            editionTypeModels[i] = new EditionTypeModel(editionTypes[i]);
        }
        return editionTypeModels;
    }
}
