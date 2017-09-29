package com.softserve.if078.tmwSpring.dao.implementation;

import com.softserve.if078.tmwSpring.configurations.H2DbConfig;
import com.softserve.if078.tmwSpring.dao.DaoInterface;
import com.softserve.if078.tmwSpring.entities.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {H2DbConfig.class})
public class StatusDaoImplTest {
@PostConstruct
public void setup(){

}
    @Autowired
    private DaoInterface dao;


    @Test
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
    public void get() throws Exception {
        Status expectedStatus = new Status();
        expectedStatus.setId(1);
        expectedStatus.setName("Ready for work");
        Status actualStatus = (Status) dao.get(expectedStatus);
        assertEquals(expectedStatus.getId(), actualStatus.getId());
        assertEquals(expectedStatus.getName(), actualStatus.getName());
    }

    @Test
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
    public void create() throws Exception {
        Status expectedStatus = new Status();
        expectedStatus.setId(5);
        expectedStatus.setName("TEST STATUS NAME");
        dao.create(expectedStatus);
        Status actualStatus = (Status) dao.get(expectedStatus);
        assertEquals(expectedStatus.getId(), actualStatus.getId());
        assertEquals(expectedStatus.getName(), actualStatus.getName());

    }



}