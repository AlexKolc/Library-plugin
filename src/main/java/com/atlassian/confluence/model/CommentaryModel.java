package com.atlassian.confluence.model;

import java.util.Date;

public class CommentaryModel {
    private int id;
    private int bookId;
    private String description;
    private Date createdDate;

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
