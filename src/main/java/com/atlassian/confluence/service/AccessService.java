package com.atlassian.confluence.service;

public interface AccessService {
    boolean hasAccess();
    boolean isUser();
    boolean isLibraryAdmin();
    boolean isAdmin();
}
