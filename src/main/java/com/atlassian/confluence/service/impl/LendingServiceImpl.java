package com.atlassian.confluence.service.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.confluence.ao.Book;
import com.atlassian.confluence.ao.Lending;
import com.atlassian.confluence.model.LendingModel;
import com.atlassian.confluence.service.LendingService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.user.UserManager;
import net.java.ao.Query;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.Objects;

@Named
public class LendingServiceImpl implements LendingService {
    @ComponentImport
    private final ActiveObjects ao;

    @ComponentImport
    private final UserManager userManager;

    @Inject
    public LendingServiceImpl(ActiveObjects ao, UserManager userManager) {
        this.ao = ao;
        this.userManager = userManager;
    }

    @Override
    public LendingModel[] getLendingByKey(String key) {
        Lending[] lendings = ao.find(Lending.class, Query.select().where("USER_KEY LIKE ?", key).order("ID DESC"));
        LendingModel[] lendingModels = new LendingModel[lendings.length];
        for (int i = 0; i < lendings.length; i++) {
            lendingModels[i] = new LendingModel(lendings[i]);
        }
        return lendingModels;
    }

    @Override
    public void addLendingBooked(int bookId) {
        Lending lending = ao.create(Lending.class);
        lending.setStatus("Забронировано");
        lending.setDateOfIssue(new Date());
        lending.setUserKey(Objects.requireNonNull(userManager.getRemoteUserKey()).getStringValue());
        lending.setUserName(Objects.requireNonNull(userManager.getRemoteUser()).getFullName());
        lending.setUserEmail(Objects.requireNonNull(userManager.getRemoteUser()).getEmail());
        lending.setBook(ao.find(Book.class, Query.select().where("ID LIKE ?", bookId))[0]);
        lending.save();
    }

    @Override
    public void addLendingPendingIssue(int bookId) {
        Lending lending = ao.create(Lending.class);
        lending.setStatus("Ожидается выдача");
        lending.setDateOfIssue(new Date());
        lending.setUserKey(Objects.requireNonNull(userManager.getRemoteUserKey()).getStringValue());
        lending.setUserName(Objects.requireNonNull(userManager.getRemoteUser()).getFullName());
        lending.setUserEmail(Objects.requireNonNull(userManager.getRemoteUser()).getEmail());
        lending.setBook(ao.find(Book.class, Query.select().where("ID LIKE ?", bookId))[0]);
        lending.save();
    }

    @Override
    public void deleteLending(int id) {
        Lending[] lendings = ao.find(Lending.class, Query.select().where("ID LIKE ?", id));
        ao.delete(lendings[0]);
    }
}
