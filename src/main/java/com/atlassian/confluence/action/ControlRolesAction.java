package com.atlassian.confluence.action;

import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.atlassian.confluence.service.AccessService;

public class ControlRolesAction extends ConfluenceActionSupport {
    public final AccessService accessService;

    public ControlRolesAction(AccessService accessService) {
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

