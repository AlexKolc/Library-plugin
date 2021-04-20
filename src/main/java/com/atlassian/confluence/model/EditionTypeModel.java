package com.atlassian.confluence.model;

import com.atlassian.confluence.ao.EditionType;
import com.atlassian.confluence.ao.Tag;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EditionTypeModel {
    @XmlElement
    private int id;
    @XmlElement
    private String typeName;

    private EditionTypeModel() {}

    public EditionTypeModel(EditionType editionType) {
        typeName= editionType.getTypeName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
