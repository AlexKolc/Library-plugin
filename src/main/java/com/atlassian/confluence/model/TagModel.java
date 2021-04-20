package com.atlassian.confluence.model;

import com.atlassian.confluence.ao.Author;
import com.atlassian.confluence.ao.Tag;

public class TagModel {
    private int id;
    private String name;

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
