package com.softserve.if078.tmwSpring.services;

import com.softserve.if078.tmwSpring.dao.AbstractDAO;
import com.softserve.if078.tmwSpring.dao.TaskDao;
import com.softserve.if078.tmwSpring.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private final String TABLE = "Tasks";
    private final String readTaskQuery = "select id from ";

    @Autowired
    private DataSource source;
    private AbstractDAO<Task> dao =new TaskDao();


    public List<Task> getTasksByassignetUser(int userId) throws SQLException {
        List<Task> result = new ArrayList<>();
        String query = readTaskQuery + TABLE +" where assign_to = ?";
        try(PreparedStatement statement = source.getConnection().prepareStatement(query)){
            statement.setInt(1, userId);
            try(ResultSet set = statement.executeQuery()){
                while (set.next()){
                    result.add(dao.get(set.getInt(1)));
                }
            }
        }
        return result;
    }



}




/*     int id;
     String name;
     Date created_date;
     Date start_date;
     Date end_date;
     Time estimate_time;
     int assign_to;
     int status_id;
     int priority_id;
     int parent_id;
 */