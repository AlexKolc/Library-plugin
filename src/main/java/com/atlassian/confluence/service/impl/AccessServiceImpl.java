package com.atlassian.confluence.service.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.confluence.service.AccessService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.user.UserManager;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class AccessServiceImpl implements AccessService {
    @ComponentImport
    private final UserManager userManager;

    @Inject
    public AccessServiceImpl(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public boolean hasAccess() {
        return userManager.getRemoteUser() != null;
    }
}
