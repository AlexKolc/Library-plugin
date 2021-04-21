package com.atlassian.confluence.service.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.confluence.ao.EditionType;
import com.atlassian.confluence.ao.Tag;
import com.atlassian.confluence.model.EditionTypeModel;
import com.atlassian.confluence.model.TagModel;
import com.atlassian.confluence.service.TagService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.user.UserManager;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class TagServiceImpl implements TagService {
    @ComponentImport
    private final ActiveObjects ao;

    @ComponentImport
    private final UserManager userManager;

    @Inject
    public TagServiceImpl(ActiveObjects ao, UserManager userManager) {
        this.ao = ao;
        this.userManager = userManager;
    }

    @Override
    public TagModel[] getTags() {
        Tag[] tags = ao.find(Tag.class);
        TagModel[] tagModels = new TagModel[tags.length];
        for (int i = 0; i < tags.length; i++) {
            tagModels[i] = new TagModel(tags[i]);
        }
        return tagModels;
    }
}
