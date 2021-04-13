package com.atlassian.confluence.ao;

import net.java.ao.Entity;
import net.java.ao.schema.Default;

public interface BookCopy extends Entity {

    String getInventoryNumber();
    void setInventoryNumber(String inventoryNumber);

    @Default("false")
    boolean getIsLost();
    void setIsLost(boolean isLost);

    // linked tables

    Book getBook();
    void setBook(Book book);
}
