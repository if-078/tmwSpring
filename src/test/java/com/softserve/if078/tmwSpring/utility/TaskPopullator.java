package com.softserve.if078.tmwSpring.utility;

import com.softserve.if078.tmwSpring.dao.PriorityDao;
import com.softserve.if078.tmwSpring.dao.TaskDao;
import com.softserve.if078.tmwSpring.entities.Task;
import com.softserve.if078.tmwSpring.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import java.sql.Date;
import java.sql.SQLException;

@TestComponent
public class TaskPopullator {

    @Autowired TaskDao taskDao;
    @Autowired UserPopulator userPopulator;
    @Autowired PriorityDao priorityDao;


    public Task createDefaultHeadTask () throws SQLException {
        int hourFromMillSecs = 1000 * 60 * 60;
        Task task = new Task();
        User defUser = userPopulator.createDefaultUser();
        task.setName("Default task name");
        task.setAssign_to(defUser.getId());
        task.setCreated_date(new Date(System.currentTimeMillis()));
        task.setEnd_date(new Date(System.currentTimeMillis() + (7 * hourFromMillSecs * 24))); //end date after one week
        task.setStart_date(new Date(System.currentTimeMillis() + (hourFromMillSecs)));
        task.setPriority_id(priorityDao.getAll().get(0).getId()); // set up first preority by default;
        return taskDao.create(task);
    }

}
