package com.atlassian.confluence.ao;

import net.java.ao.Entity;
import net.java.ao.ManyToMany;
import net.java.ao.schema.NotNull;
import net.java.ao.schema.Unique;

public interface Tag extends Entity {

    @Unique
    @NotNull
    String getName();
    String setName(String name);

    // linked tables

    @ManyToMany(value = BookToTag.class)
    Book[] getBooks();
}
