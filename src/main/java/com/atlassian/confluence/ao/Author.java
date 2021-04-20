package com.atlassian.confluence.ao;

import net.java.ao.Entity;
import net.java.ao.ManyToMany;

import java.util.Date;

public interface Author extends Entity {

    String getFullName();
    void setFullName(String fullName);

    // linked tables

    @ManyToMany(value = BookToAuthor.class)
    Book[] getBooks();
}
