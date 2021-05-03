package com.atlassian.confluence.ao;

import net.java.ao.Entity;
import net.java.ao.ManyToMany;
import net.java.ao.schema.Unique;

public interface EditionType extends Entity {

    String getTypeName();
    void setTypeName(String typeName);

    // linked tables

    @ManyToMany(value = BookToEditionType.class)
    Book[] getBooks();
}
