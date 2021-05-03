package com.atlassian.confluence.service;

import com.atlassian.confluence.model.LendingModel;

public interface LendingService {
    LendingModel[] getLendings();
    LendingModel[] getLendingByKey();
    void addLendingBooked(int bookId);
    void addLendingPendingIssue(int bookId);
    void changeStatus(int lendingId, String status);
    void deleteLending(int id);
}
