package com.atlassian.confluence.ao;

import net.java.ao.Entity;

public interface BookToTag extends Entity {

    Book getBook();
    void setBook(Book book);

    Tag getTag();
    void setTag(Tag tag);
}
