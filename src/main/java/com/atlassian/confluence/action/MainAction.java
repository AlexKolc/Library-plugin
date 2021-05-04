package com.atlassian.confluence.action;

import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.atlassian.confluence.service.AccessService;

public class MainAction extends ConfluenceActionSupport {
    public final AccessService accessService;

    public MainAction(AccessService accessService) {
        this.accessService = accessService;
    }

    public boolean hasAccess() {
        return accessService.hasAccess();
    }
}
