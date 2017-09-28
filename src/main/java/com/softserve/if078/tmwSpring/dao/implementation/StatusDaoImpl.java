package com.softserve.if078.tmwSpring.dao.implementation;



import com.softserve.if078.tmwSpring.dao.DaoInterface;
import com.softserve.if078.tmwSpring.entities.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class StatusDaoImpl implements DaoInterface<Status> {
    @Autowired
    private DataSource datasource;

    private String createQuery;
    private String readQuery;
    private String readAllQuery;
    private String updateQuery;
    private String deleteQuery;

    public StatusDaoImpl() {

        createQuery = "Insert into 'task management wizard'.'Status' (name) values (?);";
        readQuery = "Select * from 'task management wizard'.'Status' where 'task management wizard'.'Status'.status_id=?;";
        readAllQuery = "Select * from 'task management wizard'.'Status'";
        updateQuery = "Update 'task management wizard'.'Status' Set name =? Where status_id=?";
        deleteQuery = "Delete From 'task management wizard'.'Status' Where status_id=?";
    }

    public StatusDaoImpl(Properties properties) {
        createQuery = properties.getProperty("createQuery");
        readQuery = properties.getProperty("readQuery");
        readAllQuery = properties.getProperty("readAllQuery");
        updateQuery = properties.getProperty("updateQuery");
        deleteQuery = properties.getProperty("deleteQuery");
    }

    @Override
    public List<Status> getAll() {
        List<Status> statuses = new ArrayList();
        try (PreparedStatement preparedStatement = datasource.getConnection().prepareStatement(readAllQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Status status = new Status();
                    status.setId(resultSet.getInt(1));
                    status.setName(resultSet.getString(2));
                    statuses.add(status);
                }
            }
        } catch (SQLException se) {
            System.out.println("Can`t get all statuses");
        }
        return statuses;
    }

    @Override
    public Status get(Status entity) {
        Status status = null;
        try (PreparedStatement preparedStatement = datasource.getConnection().prepareStatement(readQuery)) {
            preparedStatement.setInt(1, entity.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    status = new Status();
                    status.setId(resultSet.getInt(1));
                    status.setName(resultSet.getString(2));
                    break;
                }
            }
        } catch (SQLException se) {
            System.out.println("Can`t get status");
        }
        return status;
    }

    @Override
    public void update(Status entity) {
        try (PreparedStatement preparedStatement = datasource.getConnection().prepareStatement(updateQuery)) {
            if (entity != null & !entity.getName().equals(null) & entity.getId() >= 0) {
                preparedStatement.setString(1, entity.getName());
                preparedStatement.setInt(2, entity.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException se) {
            System.out.println("Cant update status");
        }
    }

    @Override
    public void delete(Status entity) {
        try (PreparedStatement preparedStatement =datasource.getConnection().prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Can`t delete status");
        }
    }

    @Override
    public void create(Status entity) {
        try (PreparedStatement preparedStatement = datasource.getConnection().prepareStatement(createQuery)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Can`t add new status");
        }
    }
}

