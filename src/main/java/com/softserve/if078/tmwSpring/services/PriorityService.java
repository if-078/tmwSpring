package com.softserve.if078.tmwSpring.services;

import com.softserve.if078.tmwSpring.dao.AbstractDAO;
import com.softserve.if078.tmwSpring.dao.TaskDao;
import com.softserve.if078.tmwSpring.entities.Task;
import com.softserve.if078.tmwSpring.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriorityService {

    @Autowired
    DataSource dataSource;

    public List<User> getUsersWithPriority (int priorityId) throws SQLException {
        String q = "Select id from Task where priority_id = ?";
        AbstractDAO<Task> dao = new TaskDao();
        List<User> res = new ArrayList<>();
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement(q)){
            List<Task> tasks = new ArrayList<>();
            statement.setInt(1, priorityId);
            try(ResultSet set = statement.executeQuery()){
                while (set.next()){
                    tasks.add(dao.get(set.getInt(1)));
                }
            }

        }

        return res;


    }
}
