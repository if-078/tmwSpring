package com.softserve.if078.tmwSpring.entities;

import java.util.Date;

public class Comment {
    private int commentID;
    private String commentText;
    private Date createdDate;
    private int taskID;
    private int userID;

    public Comment() {
    }

    public Comment(String commentText, Date createdDate, int taskID, int userID) {
        this.commentText = commentText;
        this.createdDate = createdDate;
        this.taskID = taskID;
        this.userID = userID;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}