package com.atlassian.confluence.model;

import com.atlassian.confluence.ao.Author;
import com.atlassian.confluence.ao.Tag;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TagModel {
    @XmlElement
    private int id;
    @XmlElement
    private String name;

    private TagModel() {}

    public TagModel(Tag tag) {
        name = tag.getName();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
