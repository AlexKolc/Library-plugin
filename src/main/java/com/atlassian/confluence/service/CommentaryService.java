package com.atlassian.confluence.service;

import com.atlassian.confluence.model.CommentaryModel;

public interface CommentaryService {
    void addCommentary(CommentaryModel commentaryModel);
    void deleteCommentary(int id);
    CommentaryModel[] getCommentaries(int id);
}
