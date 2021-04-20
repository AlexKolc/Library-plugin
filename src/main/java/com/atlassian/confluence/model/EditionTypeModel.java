package com.atlassian.confluence.model;

import com.atlassian.confluence.ao.EditionType;
import com.atlassian.confluence.ao.Tag;

public class EditionTypeModel {
    private String typeName;

    public EditionTypeModel(EditionType editionType) {
        typeName= editionType.getTypeName();
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
