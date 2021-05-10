package com.atlassian.confluence.action;

import com.atlassian.confluence.core.ConfluenceActionSupport;
import com.atlassian.confluence.service.AccessService;
import com.atlassian.confluence.service.LendingService;

public class LendingsInfoAction extends ConfluenceActionSupport {
    public final AccessService accessService;
    public final LendingService lendingService;


    public LendingsInfoAction(AccessService accessService, LendingService lendingService) {
        this.accessService = accessService;
        this.lendingService = lendingService;
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

    public void checkWaitingBooks() {
        lendingService.checkWaitingBooks();
    }
}
