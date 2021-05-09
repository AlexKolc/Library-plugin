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
    private Date dateChangedStatus;
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
    private int bookId;
    @XmlElement
    private String bookName;

    // Status
    //   Забронировано
    //   Ожидается выдача
    //   На руках
    //   Возвращено
    //   Потеряно

    private LendingModel() {}

    public LendingModel(Lending lending) {
        setId(lending.getID());
        setStatus(lending.getStatus());
        setDateChangedStatus(lending.getDateChangedStatus());
        setDateOfIssue(lending.getDateOfIssue());
        setReturnPeriod(lending.getReturnPeriod());
        setReturnDate(lending.getReturnedDate());
//        setlending.getIsLost());
        setUserKey(lending.getUserKey());
        setUserName(lending.getUserName());
        setUserEmail(lending.getUserEmail());
        setBookId(lending.getBook().getID());
        setBookName(lending.getBook().getName());
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

    public Date getDateChangedStatus() {
        return dateChangedStatus;
    }

    public void setDateChangedStatus(Date dateChangedStatus) {
        this.dateChangedStatus = dateChangedStatus;
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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
