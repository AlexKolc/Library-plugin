package com.atlassian.confluence.ao;

import net.java.ao.Entity;
import net.java.ao.ManyToMany;
import net.java.ao.schema.Default;
import net.java.ao.schema.NotNull;

import java.util.Date;

public interface Lending extends Entity {

    String getStatus();
    void setStatus(String status);

    @NotNull
    Date getDateOfIssue();
    void setDateOfIssue(Date dateOfIssue);

    @NotNull
    @Default("30")
    int getReturnPeriod();
    void setReturnPeriod(int returnPeriod);

    Date getReturnedDate();
    void setReturnedDate(Date returnedDate);

//    @Default("false")
//    boolean getIsExtended();
//    void  setIsExtended(boolean isExtended);

    @Default("false")
    boolean getIsLost();
    void setIsLost(boolean isLost);

    // linked tables

    @ManyToMany(value = BookToLending.class)
    Book[] getBooks();
}
