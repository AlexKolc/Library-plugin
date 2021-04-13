package com.atlassian.confluence.ao;

import net.java.ao.Entity;
import net.java.ao.schema.Default;
import net.java.ao.schema.NotNull;

import java.util.Date;

public interface Lending extends Entity {

    @NotNull
    Date getDateOfIssue();
    void setDateOfIssue(Date dateOfIssue);

    @NotNull
    @Default("30")
    int getReturnPeriod();
    void setReturnPeriod(int returnPeriod);

    @Default("NULL")
    Date getReturnedDate();
    void setReturnedDate(Date returnedDate);

    @Default("false")
    boolean getIsExtended();
    void  setIsExtended(boolean isExtended);

    @Default("false")
    boolean getIsLost();
    void setIsLost(boolean isLost);

    // linked tables

    BookCopy getBookCopy();
    void setBookCopy(BookCopy bookCopy);
}
