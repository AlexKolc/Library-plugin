package com.atlassian.confluence.ao;

import net.java.ao.Entity;
import net.java.ao.schema.StringLength;

import java.util.Date;

public interface Commentary extends Entity {

    @StringLength(StringLength.UNLIMITED)
    String getDescription();
    void setDescription(String description);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    @StringLength(StringLength.UNLIMITED)
    String getUserKey();
    void setUserKey(String userKey);

    @StringLength(StringLength.UNLIMITED)
    String getUserName();
    void setUserName(String userName);

    // linked tables

    Book getBook();
    void setBook(Book book);
}
