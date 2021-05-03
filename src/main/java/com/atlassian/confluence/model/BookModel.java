package com.atlassian.confluence.model;

import com.atlassian.confluence.ao.Book;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookModel {
    @XmlElement
    private int id;
    @XmlElement
    private String name;
    @XmlElement
    private String isnb;
    @XmlElement
    private int yearPublishing;
    @XmlElement
    private int pageVolume;
    @XmlElement
    private int countCopies;
    @XmlElement
    private String description;
    @XmlElement
    private String imageUrl;
    @XmlElement
    private String publisher;
    @XmlElement
    private String authors;
    @XmlElement
    private String tags;
    @XmlElement
    private String editionTypes;
//    @XmlElement
//    private TagModel[] tags;
//    @XmlElement
//    private EditionTypeModel[] editionTypes;

    private BookModel() {}

    public BookModel(Book book) {
        setId(book.getID());
        setName(book.getName());
        setIsnb(book.getIsnb());
        setYearPublishing(book.getYearPublishing());
        setPageVolume(book.getPageVolume());
        setCountCopies(book.getCountCopies());
        setDescription(book.getDescription());
        setImageUrl(book.getImageUrl());
        setPublisher(book.getPublisher());
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

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getEditionTypes() {
        return editionTypes;
    }

    public void setEditionTypes(String editionTypes) {
        this.editionTypes = editionTypes;
    }

//    public TagModel[] getTags() {
//        return tags;
//    }
//
//    public void setTags(TagModel[] tags) {
//        this.tags = tags;
//    }
//
//    public EditionTypeModel[] getEditionTypes() {
//        return editionTypes;
//    }
//
//    public void setEditionTypes(EditionTypeModel[] editionTypes) {
//        this.editionTypes = editionTypes;
//    }
}
