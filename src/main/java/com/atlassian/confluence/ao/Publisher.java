package com.atlassian.confluence.ao;

import net.java.ao.Entity;
import net.java.ao.OneToMany;

public interface Publisher extends Entity {

    String getName();
    void setName();

    String getCity();
    void setCity(String city);

    // linked tables

    @OneToMany
    Book[] getBooks();
}
