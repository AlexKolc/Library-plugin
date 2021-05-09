package com.atlassian.confluence.service;

import com.atlassian.confluence.model.TagModel;

public interface TagService {
    TagModel[] getTags();
    int addTag(TagModel tagModel);
}
