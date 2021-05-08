package com.atlassian.confluence.service;

import com.atlassian.confluence.model.CommentaryModel;

public interface CommentaryService {
    CommentaryModel addCommentary(CommentaryModel commentaryModel);
    void deleteCommentary(int id);
    CommentaryModel[] getCommentaries(int id);
}
