package com.softserve.if078.tmwSpring.controllers;

import com.softserve.if078.tmwSpring.entities.Comment;
import com.softserve.if078.tmwSpring.entities.Tag;
import com.softserve.if078.tmwSpring.entities.Task;
import com.softserve.if078.tmwSpring.entities.User;
import com.softserve.if078.tmwSpring.services.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskServiceInterface taskDao;
    /*@Autowired
    TaskDao taskDao;*/


    @PostMapping("/")
    void createTask(@RequestBody Task task) {
        taskDao.create(task);
    }


    @PutMapping("/")
    void updateTask(@RequestBody Task task) {
        taskDao.update(task);
    }


    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable int id) {
        taskDao.delete(id);
    }



    @GetMapping("/")
    List<Task> getAllTasks() {
        return taskDao.getAll();
    }

    @GetMapping("/{id}")
    Task getTask(@PathVariable int id) {
        return taskDao.get(id);
    }

    @GetMapping("/tag/{id}")
    List<Task> tasksByTag(@PathVariable int id) {
        return taskDao.getTasksByTag(id);
    }

    @GetMapping("/priority/{id}")
    List<Task> tasksByPriority(@PathVariable int id) {
        return taskDao.getTaskByPriority(id);
    }

    @GetMapping("/status/{id}")
    List<Task> tasksByStatus(@PathVariable int id) {
        return taskDao.getTaskByStatus(id);
    }

    @GetMapping("/today")
    List<Task> tasksForToday() {
        return taskDao.getTasksForToday();
    }

    @GetMapping("/assign_to/{id}")
    List<Task> assignTo(@PathVariable int id) {
        return taskDao.getTasksAssignToUser(id);
    }

    @GetMapping("/created_by/{id}")
    List<Task> createdBy(@PathVariable int id) {
        return taskDao.getTasksCreatedByUser(id);
    }

    @GetMapping("/tree_of/{id}")
    List<Task> treeOfTasks(@PathVariable int id) {
        return taskDao.treeOfTasks(id);
    }

    @GetMapping("/author_of_task/{id}")
    User authorOfTssk(@PathVariable int id) {
        return taskDao.getAuthorOfTask(id);
    }

    @GetMapping("/tags_of/{id}")
    List<Tag> tagsOfTask(@PathVariable int id) {
        return taskDao.getTagsOfTask(id);
    }

    @GetMapping("/comments_of/{id}")
    List<Comment> commentsOfTask(@PathVariable int id) {
        return taskDao.getCommentsOfTask(id);
    }



    /*@PostMapping("/")
    void createUser(@RequestBody User user) {
        userService.create(user);
    }

    @GetMapping("/{userid}")
    User getUser(@PathVariable Integer userid) {
        return userService.get(userid);
    }

    @PutMapping("/{userid}")
    void updateUser(@RequestBody User user, @PathVariable Integer userid) {
        userService.update(user, userid);
    }

    @DeleteMapping("/{userid}")
    void deleteUser(@PathVariable Integer userid) {
        userService.delete(userid);
    }*/

}
