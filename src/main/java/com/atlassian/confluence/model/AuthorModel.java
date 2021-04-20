package com.atlassian.confluence.model;

import com.atlassian.confluence.ao.Author;

public class AuthorModel {
    private int id;
    private String fullName;

    public AuthorModel(Author author) {
        fullName = author.getFullName();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
