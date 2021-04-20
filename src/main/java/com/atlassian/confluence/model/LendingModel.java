package com.atlassian.confluence.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class LendingModel {
    @XmlElement
    private int id;
    @XmlElement
    private String status;
    @XmlElement
    private Date dateOfIssue;
    @XmlElement
    private int returnPeriod;
    @XmlElement
    private Date returnDate;
    @XmlElement
    private boolean isLost;

    private LendingModel() {}

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
