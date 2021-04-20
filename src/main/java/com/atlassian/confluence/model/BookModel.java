package com.atlassian.confluence.model;

import com.atlassian.confluence.ao.Book;

public class BookModel {
    private int id;
    private String name;
    private String isnb;
    private int yearPublishing;
    private int pageVolume;
    private int countCopies;
    private String description;
    private String imageUrl;
    private String publisher;
    private AuthorModel[] authors;
    private TagModel[] tags;
    private EditionTypeModel[] editionTypes;

    public BookModel(Book book) {
        setId(book.getID());
        setName(book.getName());
        setIsnb(book.getIsnb());
        setYearPublishing(book.getYearPublishing());
        setPageVolume(book.getPageVolume());
        setCountCopies(book.getCountCopies());
        setDescription(book.getDescription());
        setImageUrl(book.getImageUrl());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsnb() {
        return isnb;
    }

    public void setIsnb(String isnb) {
        this.isnb = isnb;
    }

    public int getYearPublishing() {
        return yearPublishing;
    }

    public void setYearPublishing(int yearPublishing) {
        this.yearPublishing = yearPublishing;
    }

    public int getPageVolume() {
        return pageVolume;
    }

    public void setPageVolume(int pageVolume) {
        this.pageVolume = pageVolume;
    }

    public int getCountCopies() {
        return countCopies;
    }

    public void setCountCopies(int countCopies) {
        this.countCopies = countCopies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public AuthorModel[] getAuthors() {
        return authors;
    }

    public void setAuthors(AuthorModel[] authors) {
        this.authors = authors;
    }

    public TagModel[] getTags() {
        return tags;
    }

    public void setTags(TagModel[] tags) {
        this.tags = tags;
    }

    public EditionTypeModel[] getEditionTypes() {
        return editionTypes;
    }

    public void setEditionTypes(EditionTypeModel[] editionTypes) {
        this.editionTypes = editionTypes;
    }
}
