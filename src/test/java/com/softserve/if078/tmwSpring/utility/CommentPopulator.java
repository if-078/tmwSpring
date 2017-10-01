package com.softserve.if078.tmwSpring.utility;

import com.softserve.if078.tmwSpring.dao.CommentDaoImpl;
import com.softserve.if078.tmwSpring.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import java.sql.SQLException;

@TestComponent
public class CommentPopulator {

    @Autowired CommentDaoImpl dao;
    @Autowired UserPopulator userPopulator;
    @Autowired TaskPopullator taskPopullator;


    public Comment createDefaultComment() throws SQLException {
        Comment comment = new Comment();
        comment.setCommentText("Default comment text");
        comment.setUserId(userPopulator.createDefaultUser().getId());
        comment.setTaskId(taskPopullator.getTask().getId()git bra);
    }


}
