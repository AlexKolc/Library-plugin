//package com.atlassian.confluence.service.impl;
//
//import com.atlassian.activeobjects.external.ActiveObjects;
//import com.atlassian.confluence.ao.Commentary;
//import com.atlassian.confluence.model.CommentaryModel;
//import com.atlassian.confluence.service.CommentaryService;
//import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
//import com.atlassian.sal.api.user.UserManager;
//
//import javax.inject.Inject;
//
//public class CommentaryServiceImpl implements CommentaryService {
//    @ComponentImport
//    private final ActiveObjects ao;
//
//    @ComponentImport
//    private final UserManager userManager;
//
//    @Inject
//    public CommentaryServiceImpl(ActiveObjects ao, UserManager userManager) {
//        this.ao = ao;
//        this.userManager = userManager;
//    }
//
//    @Override
//    public void addCommentary(CommentaryModel commentaryModel) {
//        Commentary commentary = ao.create(Commentary.class);
//        //commentary.setBook();
//        commentary.setDescription(commentaryModel.getDescription());
////        commentary.setCreatedDate();
//        commentary.save();
//    }
//
//    @Override
//    public void deleteCommentary(int id) {
//
//    }
//
//    @Override
//    public CommentaryModel[] getCommentaries(int id) {
//        return new CommentaryModel[0];
//    }
//}
