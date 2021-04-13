package com.atlassian.confluence.ao;

import net.java.ao.Entity;

public interface BookToEditionType extends Entity {

    Book getBook();
    void setBook(Book book);

    EditionType getEditionType();
    void setEditionType(EditionType editionType);
}
