package com.atlassian.confluence.service.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.confluence.service.AccessService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.user.UserManager;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

@Named
public class AccessServiceImpl implements AccessService {
    private final String USER_GROUP = "library-users";
    private final String ADMIN_LIBRARY_GROUP = "library-administrators";
    private final String ADMIN_GROUP = "confluence-administrators";


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

    @Override
    public boolean isUser() {
        return userManager.isUserInGroup(userManager.getRemoteUserKey(), USER_GROUP);
    }

    @Override
    public boolean isLibraryAdmin() {
        return userManager.isUserInGroup(userManager.getRemoteUserKey(), ADMIN_LIBRARY_GROUP);
    }

    @Override
    public boolean isAdmin() {
        return userManager.isUserInGroup(userManager.getRemoteUserKey(), ADMIN_GROUP);
    }
}
