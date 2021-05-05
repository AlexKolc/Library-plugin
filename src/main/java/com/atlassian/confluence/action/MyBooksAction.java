package com.atlassian.confluence.action;

import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.atlassian.confluence.service.AccessService;

public class MyBooksAction extends ConfluenceActionSupport {
    public final AccessService accessService;

    public MyBooksAction(AccessService accessService) {
        this.accessService = accessService;
    }

    public boolean hasAccess() {
        return accessService.hasAccess();
    }

    public boolean isUser() {
        return accessService.isUser();
    }
}
