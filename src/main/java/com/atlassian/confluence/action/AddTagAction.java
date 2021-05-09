package com.atlassian.confluence.action;

import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.atlassian.confluence.service.AccessService;

public class AddTagAction extends ConfluenceActionSupport {
    public final AccessService accessService;

    public AddTagAction(AccessService accessService) {
        this.accessService = accessService;
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
