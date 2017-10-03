package com.softserve.if078.tmwSpring.utility;

import com.softserve.if078.tmwSpring.dao.CommentDaoImpl;
import com.softserve.if078.tmwSpring.entities.Comment;
import com.softserve.if078.tmwSpring.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import java.sql.SQLException;

@TestComponent
public class CommentPopulator {

    private CommentDaoImpl dao;
    private UserPopulator userPopulator;
    private TaskPopullator taskPopullator;

    @Autowired
    public void setDao(CommentDaoImpl dao) {
        this.dao = dao;
    }

    @Autowired
    public void setUserPopulator(UserPopulator userPopulator) {
        this.userPopulator = userPopulator;
    }

    @Autowired
    public void setTaskPopullator(TaskPopullator taskPopullator) {
        this.taskPopullator = taskPopullator;
    }

    public Comment createDefaultComment() throws SQLException {
        Comment comment = new Comment();
        comment.setCommentText("Default comment text");
        comment.setTaskId(1);
        comment.setUserId(userPopulator.createDefaultUser().getId());
        comment.setCommentId(-1);
        return dao.create(comment);
    }
}