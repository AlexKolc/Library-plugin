package com.atlassian.confluence.ao;

import net.java.ao.Entity;

public interface BookToLending  extends Entity {
    Book getBook();
    void setBook(Book book);

    Lending getLending();
    void setLending(Lending lending);

}
