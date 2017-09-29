package com.softserve.if078.tmwSpring.services;

import com.softserve.if078.tmwSpring.dao.TaskDaoInterface;
import com.softserve.if078.tmwSpring.entities.Comment;
import com.softserve.if078.tmwSpring.entities.Tag;
import com.softserve.if078.tmwSpring.entities.Task;
import com.softserve.if078.tmwSpring.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService implements TaskServiceInterface {

    @Autowired
    TaskDaoInterface taskDao;

    @Override
    public List<Task> getAll() {
        return taskDao.getAll();
    }

    @Override
    public Task get(int id) {
        return taskDao.get(id);
    }

    @Override
    public void update(Task task) {
        taskDao.update(task);
    }

    @Override
    public void delete(int id) {
        taskDao.delete(id);
    }

    @Override
    public void create(Task task) {
        taskDao.create(task);
    }

    @Override
    public ArrayList<Task> getTaskByStatus(int statusId) {
        return taskDao.getTaskByStatus(statusId);
    }

    @Override
    public ArrayList<Task> getTaskByPriority(int priorityId) {
        return taskDao.getTaskByPriority(priorityId);
    }

    @Override
    public ArrayList<Task> getTasksCreatedByUser(int userId) {
        return taskDao.getTasksCreatedByUser(userId);
    }

    @Override
    public ArrayList<Task> getTasksAssignToUser(int userId) {
        return taskDao.getTasksAssignToUser(userId);
    }

    @Override
    public ArrayList<Task> getTasksForToday() {
        return taskDao.getTasksForToday();
    }

    @Override
    public ArrayList<Task> getTasksByTag(int tagId) {
        return taskDao.getTasksByTag(tagId);
    }

    @Override
    public  ArrayList<Task> treeOfTasks(int taskId) {
        return taskDao.treeOfTasks(taskId);
    }

    @Override
    public User getAuthorOfTask(int taskId) {
        return taskDao.getAuthorOfTask(taskId);
    }

    @Override
    public ArrayList<Tag> getTagsOfTask(int taskId) {
        return taskDao.getTagsOfTask(taskId);
    }

    @Override
    public ArrayList<Comment> getCommentsOfTask(int taskId) {
        return taskDao.getCommentsOfTask(taskId);
    }

}
