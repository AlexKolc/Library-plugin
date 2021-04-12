package com.atlassian.confluence.ao;

import net.java.ao.Entity;

public interface Book extends Entity {

    String getName();

    void setName(String name);

    String getIsnb();

    void setIsnb(String isnb);

    int getYearPublishing();

    void setYearPublishing(int yearPublishing);

    int getPageVolume();

    void setPageVolume(int pageVolume);
}
