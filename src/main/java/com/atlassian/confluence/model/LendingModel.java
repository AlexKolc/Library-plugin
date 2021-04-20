package com.atlassian.confluence.model;

import java.util.Date;

public class LendingModel {
    private int id;
    private String status;
    private Date dateOfIssue;
    private int returnPeriod;
    private Date returnDate;
    private boolean isLost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public int getReturnPeriod() {
        return returnPeriod;
    }

    public void setReturnPeriod(int returnPeriod) {
        this.returnPeriod = returnPeriod;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean getIsLost() {
        return isLost;
    }

    public void setIsLost(boolean isLost) {
        this.isLost = isLost;
    }
}
