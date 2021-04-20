package com.atlassian.confluence.model;

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

    private CommentaryModel() {}

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

    Date getCreatedDate() {
        return createdDate;
    }

    void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
