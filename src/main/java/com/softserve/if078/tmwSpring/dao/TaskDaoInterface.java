package com.softserve.if078.tmwSpring.dao;

import com.softserve.if078.tmwSpring.entities.Comment;
import com.softserve.if078.tmwSpring.entities.Tag;
import com.softserve.if078.tmwSpring.entities.Task;
import com.softserve.if078.tmwSpring.entities.User;

import java.util.ArrayList;

public interface TaskDaoInterface extends DaoInterface<Task> {

    public ArrayList<Task> getTaskByStatus(int statusId);

    public ArrayList<Task> getTaskByPriority(int priorityId);

    public ArrayList<Task> getTasksCreatedByUser(int userId);

    public ArrayList<Task> getTasksAssignToUser(int userId);

    public ArrayList<Task> getTasksForToday();

    public ArrayList<Task> getTasksByTag(int tagId);

    public  ArrayList<Task> treeOfTasks(int taskId);

    public User getAuthorOfTask(int taskId);

    public ArrayList<Tag> getTagsOfTask(int taskId);

    public ArrayList<Comment> getCommentsOfTask(int taskId);

}
