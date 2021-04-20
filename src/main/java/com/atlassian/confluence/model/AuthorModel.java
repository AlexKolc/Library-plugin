package com.atlassian.confluence.model;

import com.atlassian.confluence.ao.Author;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthorModel {
    @XmlElement
    private int id;

    @XmlElement
    private String fullName;

    private AuthorModel() {};

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
