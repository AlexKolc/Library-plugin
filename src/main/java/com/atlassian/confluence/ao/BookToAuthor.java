package com.atlassian.confluence.ao;

import net.java.ao.Entity;

public interface BookToAuthor extends Entity {

    Book getBook();
    void setBook(Book book);

    Author getAuthor();
    void setAuthor(Author author);

}
