package com.atlassian.confluence.action;

import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.atlassian.confluence.service.AccessService;

public class CatalogAction extends ConfluenceActionSupport {
    public final AccessService accessService;

    public CatalogAction(AccessService accessService) {
        this.accessService = accessService;
    }

    public boolean hasAccess() {
        return accessService.hasAccess();
    }

    public boolean isUser() {
        return accessService.isUser();
    }
}
