package com.softserve.if078.tmwSpring.dao.implementation;

import com.softserve.if078.tmwSpring.dao.DaoInterface;
import com.softserve.if078.tmwSpring.entities.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {StatusDaoImplTest.TestConfig.class})
@Sql("h2_script.sql")
public class StatusDaoImplTest {

    @Autowired
    private DaoInterface dao;
    private Properties properties;

    public StatusDaoImplTest() {
        properties = new Properties();
        setProp();
        dao = new StatusDaoImpl(properties);
    }

    private void setProp() {
        String createQuery = "insert into \"task management wizard\".\"Status\" (name) values (?);";
        String readQuery = "Select * from \"task management wizard\".\"Status\" where \"task management wizard\".\"Status\".status_id=?;";
        String readAllQuery = "Select * from \"task management wizard\".\"Status\"";
        String updateQuery = "Update \"task management wizard\".\"Status\" Set name =? Where status_id=?";
        String deleteQuery = "Delete From \"task management wizard\".\"Status\" Where status_id=?";
        properties.setProperty("createQuery", createQuery);
        properties.setProperty("readQuery", readQuery);
        properties.setProperty("readAllQuery", readAllQuery);
        properties.setProperty("updateQuery", updateQuery);
        properties.setProperty("deleteQuery", deleteQuery);
    }

    @Test
    @SqlGroup({@Sql("init_status_table.sql"), @Sql(scripts = "clear_status_table.sql", executionPhase = AFTER_TEST_METHOD)})
    public void getAll() throws Exception {
        Status status = new Status();
        status.setId(1);
        status.setName("Ready for work");
        Status status1 = new Status();
        status1.setId(2);
        status1.setName("In progress");
        Status status2 = new Status();
        status2.setId(3);
        status2.setName("Review");
        Status status3 = new Status();
        status3.setId(4);
        status3.setName("Done");
        List<Status> expectedStatuse = new ArrayList<>();
        expectedStatuse.add(status);
        expectedStatuse.add(status1);
        expectedStatuse.add(status2);
        expectedStatuse.add(status3);
        List<Status> actualStatuses = dao.getAll();
        assertEquals(expectedStatuse.size(), actualStatuses.size());
        for (int i = 0; i < expectedStatuse.size(); i++) {
            assertEquals(expectedStatuse.get(i).getId(), expectedStatuse.get(i).getId());
            assertEquals(expectedStatuse.get(i).getName(), expectedStatuse.get(i).getName());
        }
    }

    @Test
    @SqlGroup({@Sql("init_status_table.sql"), @Sql(scripts = "clear_status_table.sql", executionPhase = AFTER_TEST_METHOD)})
    public void get() throws Exception {
        Status expectedStatus = new Status();
        expectedStatus.setId(1);
        expectedStatus.setName("Ready for work");
        Status actualStatus = (Status) dao.get(expectedStatus);
        assertEquals(expectedStatus.getId(), actualStatus.getId());
        assertEquals(expectedStatus.getName(), actualStatus.getName());
    }

    @Test
    @SqlGroup({@Sql("init_status_table.sql"), @Sql(scripts = "clear_status_table.sql", executionPhase = AFTER_TEST_METHOD)})
    public void update() throws Exception {
        Status expectedStatus = new Status();
        expectedStatus.setId(1);
        expectedStatus.setName("UPDATE STATUS NAME");
        dao.update(expectedStatus);
        Status actualStatus = (Status) dao.get(expectedStatus);
        assertEquals(expectedStatus.getId(), actualStatus.getId());
        assertEquals(expectedStatus.getName(), actualStatus.getName());
    }

    @Test
    @SqlGroup({@Sql("init_status_table.sql"), @Sql(scripts = "clear_status_table.sql", executionPhase = AFTER_TEST_METHOD)})
    public void delete() throws Exception {
        Status status = new Status();
        status.setId(1);
        status.setName("Ready for work");
        Status status1 = new Status();
        status1.setId(2);
        status1.setName("In progress");
        Status status2 = new Status();
        status2.setId(3);
        status2.setName("Review");
        Status status3 = new Status();
        status3.setId(4);
        status3.setName("Done");
        List<Status> expectedStatuses = new ArrayList<>();
        expectedStatuses.add(status);
        expectedStatuses.add(status1);
        expectedStatuses.add(status2);
        expectedStatuses.add(status3);

        dao.delete(status);
        List<Status> actualStatuses = dao.getAll();
        assertEquals(actualStatuses.size(), 3);
        for (int i = 0; i < actualStatuses.size(); i++) {
            assertEquals(expectedStatuses.get(i + 1).getId(), actualStatuses.get(i).getId());
            assertEquals(expectedStatuses.get(i + 1).getName(), actualStatuses.get(i).getName());
        }
    }

    @Test
    @SqlGroup({@Sql("init_status_table.sql"), @Sql(scripts = "clear_status_table.sql", executionPhase = AFTER_TEST_METHOD)})
    public void create() throws Exception {
        Status expectedStatus = new Status();
        expectedStatus.setId(5);
        expectedStatus.setName("TEST STATUS NAME");
        dao.create(expectedStatus);
        Status actualStatus = (Status) dao.get(expectedStatus);
        assertEquals(expectedStatus.getId(), actualStatus.getId());
        assertEquals(expectedStatus.getName(), actualStatus.getName());

    }

    @Configuration
    @PropertySource("classpath:datasource-test.properties")
    static class TestConfig {
        @Autowired
        Environment environment;

        @Bean
        public DataSource getTestdataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
            dataSource.setUrl(environment.getProperty("jdbc.url"));
            dataSource.setUsername(environment.getProperty("jdbc.username"));
            dataSource.setPassword(environment.getProperty("jdbc.password"));
            return dataSource;

        }

        @Bean
        public DaoInterface getStatusDao() {
            DaoInterface dao = new StatusDaoImpl();
            return dao;
        }

    }

}