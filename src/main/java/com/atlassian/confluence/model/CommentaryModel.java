package com.atlassian.confluence.model;

import com.atlassian.confluence.ao.Commentary;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class CommentaryModel {
    @XmlElement
    private int id;
    @XmlElement
    private int bookId;
    @XmlElement
    private String description;
    @XmlElement
    private Date createdDate;
    @XmlElement
    private String userKey;
    @XmlElement
    private String userName;
//    @XmlElement
//    private String user;

    private CommentaryModel() {}

    public CommentaryModel(Commentary commentary) {
        id = commentary.getID();
        bookId = commentary.getID();
        description = commentary.getDescription();
        createdDate = commentary.getCreatedDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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
}
