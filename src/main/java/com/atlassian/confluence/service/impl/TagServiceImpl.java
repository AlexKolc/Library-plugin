package com.atlassian.confluence.service.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.confluence.ao.EditionType;
import com.atlassian.confluence.ao.Tag;
import com.atlassian.confluence.model.EditionTypeModel;
import com.atlassian.confluence.model.TagModel;
import com.atlassian.confluence.service.TagService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.user.UserManager;
import net.java.ao.Query;

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

    @Override
    public int addTag(TagModel tagModel) {
        Tag[] tags = ao.find(Tag.class, Query.select().where("NAME LIKE ?", tagModel.getName()));
        if (tags.length != 0)
            return 208;
        Tag tag = ao.create(Tag.class);
        tag.setName(tagModel.getName());
        tag.save();
        return 200;
    }
}
