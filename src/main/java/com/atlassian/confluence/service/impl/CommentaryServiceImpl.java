package com.atlassian.confluence.service.impl;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.confluence.ao.Book;
import com.atlassian.confluence.ao.Commentary;
import com.atlassian.confluence.model.CommentaryModel;
import com.atlassian.confluence.service.CommentaryService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.user.UserManager;
import net.java.ao.Query;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Named
public class CommentaryServiceImpl implements CommentaryService {
    @ComponentImport
    private final ActiveObjects ao;

    @ComponentImport
    private final UserManager userManager;

    @Inject
    public CommentaryServiceImpl(ActiveObjects ao, UserManager userManager) {
        this.ao = ao;
        this.userManager = userManager;
    }

    @Override
    public CommentaryModel addCommentary(CommentaryModel commentaryModel) {
        Commentary commentary = ao.create(Commentary.class);
        commentary.setBook(ao.find(Book.class, Query.select().where("ID LIKE ?", commentaryModel.getBookId()))[0]);
        commentary.setDescription(commentaryModel.getDescription());
        commentary.setCreatedDate(new Date());
        commentary.setUserKey(Objects.requireNonNull(userManager.getRemoteUserKey()).getStringValue());
        commentary.setUserName(Objects.requireNonNull(userManager.getRemoteUser()).getFullName());
        commentary.save();

        commentaryModel.setCreatedDate(commentary.getCreatedDate());
        commentaryModel.setUserKey(commentary.getUserKey());
        commentaryModel.setUserName(commentary.getUserName());
        return commentaryModel;
    }

    @Override
    public void deleteCommentary(int id) {
        Commentary[] commentaries = ao.find(Commentary.class, Query.select().where("ID LIKE", id));
        if (commentaries != null)
            ao.delete(commentaries[0]);
    }

    @Override
    public CommentaryModel[] getCommentaries(int bookId) {
        Commentary[] commentaries = ao.find(Commentary.class, Query.select().where("BOOK_ID LIKE ?", bookId));
        CommentaryModel[] commentaryModels = new CommentaryModel[commentaries.length];
        for (int i = 0; i < commentaries.length; i++) {
            commentaryModels[i] = new CommentaryModel(commentaries[i]);
        }
        return commentaryModels;
    }
}
