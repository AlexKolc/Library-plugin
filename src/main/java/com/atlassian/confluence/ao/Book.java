package com.atlassian.confluence.ao;

import net.java.ao.Entity;
import net.java.ao.ManyToMany;
import net.java.ao.OneToMany;
import net.java.ao.schema.StringLength;

public interface Book extends Entity {

    String getName();
    void setName(String name);

    String getIsnb();
    void setIsnb(String isnb);

    int getYearPublishing();
    void setYearPublishing(int yearPublishing);

    int getPageVolume();
    void setPageVolume(int pageVolume);

    int getCountCopies();
    void setCountCopies(int countCopies);

    @StringLength(StringLength.UNLIMITED)
    String getPublisher();
    void setPublisher(String publisher);

    String getImageUrl();
    void setImageUrl(String imageUrl);

    @StringLength(StringLength.UNLIMITED)
    String getDescription();
    void setDescription(String description);

    // linked tables

    @ManyToMany(value = BookToAuthor.class)
    Author[] getAuthors();

    @ManyToMany(value = BookToTag.class)
    Tag[] getTags();

    @ManyToMany(value = BookToEditionType.class)
    EditionType[] getEditionTypes();

    @OneToMany
    Lending[] getLendings();

    @OneToMany
    Commentary[] getCommentaries();
}
