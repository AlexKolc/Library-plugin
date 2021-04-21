package com.atlassian.confluence.service;

import com.atlassian.confluence.ao.EditionType;
import com.atlassian.confluence.model.BookModel;
import com.atlassian.confluence.model.EditionTypeModel;

public interface EditionTypeService {
    EditionTypeModel[] getEditionTypes();
}
