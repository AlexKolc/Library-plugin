package com.atlassian.confluence.model;

import com.atlassian.confluence.ao.Lending;

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
    @XmlElement
    private String userKey;
    @XmlElement
    private String userName;
    @XmlElement
    private String userEmail;
    @XmlElement
    private String bookId;

    // Status
    //   Забронировано
    //   Ожидается выдача
    //   На руках
    //   Возвращено
    //   Потеряно

    private LendingModel() {}

    public LendingModel(Lending lending) {
        id = lending.getID();
        status = lending.getStatus();
        dateOfIssue = lending.getDateOfIssue();
        returnPeriod = lending.getReturnPeriod();
        returnDate = lending.getReturnedDate();
        isLost = lending.getIsLost();
        userKey = lending.getUserKey();
        userName = lending.getUserName();
        userEmail = lending.getUserEmail();
    }

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

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
