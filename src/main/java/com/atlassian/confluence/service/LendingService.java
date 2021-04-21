package com.atlassian.confluence.service;

import com.atlassian.confluence.model.LendingModel;

public interface LendingService {
    LendingModel[] getLendingByKey(String key);
    void addLendingBooked(int bookId);
    void addLendingPendingIssue(int bookId);
    void deleteLending(int id);
}
