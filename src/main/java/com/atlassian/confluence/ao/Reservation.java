package com.atlassian.confluence.ao;

import net.java.ao.Entity;
import net.java.ao.schema.Default;

import java.util.Date;

public interface Reservation extends Entity {

    Date getDateReservation();
    void setDateReservation(Date dateReservation);

    @Default("true")
    boolean getIsActive();
    void setIsActive(boolean isActive);

    // linked tables

    Book getBook();
    void setBook(Book book);
}
