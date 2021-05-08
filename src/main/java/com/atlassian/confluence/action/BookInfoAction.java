package com.atlassian.confluence.action;

import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.atlassian.confluence.service.AccessService;

public class BookInfoAction extends ConfluenceActionSupport {
    public int id;

    public final AccessService accessService;

    public BookInfoAction(AccessService accessService) {
        this.accessService = accessService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean hasAccess() {
        return accessService.hasAccess();
    }

    public boolean isUser() {
        return accessService.isUser();
    }

    public boolean isLibraryAdmin() {
        return accessService.isLibraryAdmin();
    }

    public boolean isAdmin() {
        return accessService.isAdmin();
    }
}
