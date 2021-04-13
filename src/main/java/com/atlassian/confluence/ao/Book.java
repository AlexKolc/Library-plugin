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
    String getDescription();
    void setDescription(String description);

    // linked tables

    Publisher getPublisher();
    void setPublisher(Publisher publisher);

    @ManyToMany(value = BookToAuthor.class)
    Author[] getAuthors();

    @ManyToMany(value = BookToTag.class)
    Tag[] getTags();

    @ManyToMany(value = BookToEditionType.class)
    EditionType[] getEditionTypes();

    @OneToMany
    BookCopy[] getBookCopies();

    @OneToMany
    Reservation[] getReservations();

    @OneToMany
    Commentary[] getCommentaries();
}
