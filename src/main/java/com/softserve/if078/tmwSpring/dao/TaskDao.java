package com.softserve.if078.tmwSpring.dao;

import com.softserve.if078.tmwSpring.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDao implements AbstractDAO<Task> {

    @Autowired
    DataSource source;

    @Override
    public List<Task> getAll() throws SQLException {
        return null;
    }

    @Override
    public Task get(int id) throws SQLException {
        return null;
    }

    @Override
    public void update(Task entity) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public void create(Task entity) throws SQLException {

    }

    //example method
    public List<Task> getTasksByStringField (String f) throws SQLException {
        String q = "SELECT * FROM tasks WHERE 'FIELD' = " + f;
        List<Task> res = new ArrayList<>();
        try (ResultSet set = source.getConnection().prepareStatement(q).executeQuery()){
            while (set.next()){
                Task task = new Task();
                task.setId(set.getInt(1));
                task.setAssign_to(set.getInt(2));
                /*
                ...
                 */
                res.add(task);
            }
        }
        return res;
    }
}
