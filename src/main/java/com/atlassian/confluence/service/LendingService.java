package com.atlassian.confluence.service;

import com.atlassian.confluence.model.LendingModel;

public interface LendingService {
    LendingModel[] getLendings();
    LendingModel[] getLendingByKey();
    int addLendingBooked(int bookId);
    int addLendingPendingIssue(int bookId);
    LendingModel changeStatus(int lendingId, String status);
    void deleteLending(int id);
    void checkWaitingBooks();
}
