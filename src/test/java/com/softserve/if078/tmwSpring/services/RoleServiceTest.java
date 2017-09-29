package com.softserve.if078.tmwSpring.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.softserve.if078.tmwSpring.TmwSpringApplicationTests;
import com.softserve.if078.tmwSpring.configurations.H2DbConfig;
import com.softserve.if078.tmwSpring.entities.User;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.softserve.if078.tmwSpring")
@SpringBootTest(classes = { TmwSpringApplicationTests.class, H2DbConfig.class })
@EnableConfigurationProperties
public class RoleServiceTest {

    @Autowired
    RoleService roleService;

    private RoleDaoImpl dao = null;
    private Connector connector = null;
    private ScriptRunner runner;
    private Properties properties;

    @Before
    public void initDbAddRoles() throws IOException, SQLException {
        connector = new Connector("connect_to_h2.properties");
        runner = new ScriptRunner(connector.getConnection(), true, true);
        properties = new Properties();
        dao = new RoleDaoImpl(connector);
        runner.runScript(new BufferedReader(new FileReader("src\\test\\resources\\sql\\" +
                "queriesPreparationForTest\\h2_script.sql")));
        runner.runScript(new BufferedReader(new FileReader("src\\test\\resources\\sql\\" +
                "queriesPreparationForTest\\roleTable\\init_role_table.sql")));
    }

    @After
    public void dropTable() throws IOException, SQLException {
        runner.runScript(new BufferedReader(new FileReader("src\\test\\resources\\sql\\" +
                "queriesPreparationForTest\\roleTable\\clear_role_table.sql")));
    }

    @Test
    public void testCreateAndUpdateAndGet() throws Exception {
        Role role = new Role("asdf");
        dao.create(role);
        assertEquals("asdf", dao.get(role).getName());
        role.setName("fdsa");
        dao.update(role);
        assertEquals("fdsa", dao.get(role).getName());
    }

    @Test
    public void testCreateAndGetAndDelete() throws Exception {
        Role role = new Role("asdf");
        dao.create(role);
        assertNotNull(dao.get(role));
        dao.delete(role);
        assertNull(dao.get(role));
    }

    @Test
    public void testCreateAndGetAllAndDeleteAll() throws Exception {
        dao.create(new Role("zxcv3"), new Role("zxcv4"), new Role("zxcv5"));
        assertEquals(5, dao.getAll().size());
        for (Role role : dao.getAll()) {
            if (role.getId() > 2) assertEquals("zxcv" + role.getId(), role.getName());
        }
        dao.deleteAll();
        assertEquals(0, dao.getAll().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateArgumentNegative() throws Exception {
        dao.create(new Role(-1, "a"));
    }

    @Test(expected = NullPointerException.class)
    public void testCreateObjectNegative() throws Exception {
        dao.create(new Role());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetArgumentNegative() throws Exception {
        dao.get(new Role(-1, null));
    }

    @Test(expected = NullPointerException.class)
    public void testGetObjectNegative() throws Exception {
        dao.get(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateArgumentNegative() throws Exception {
        dao.update(new Role(-1, "a"));
        dao.update(new Role(""));
        dao.update(new Role(null));
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateObjectNegative() throws Exception {
        dao.update(new Role());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteArgumentNegative() throws Exception {
        dao.get(new Role(-5, ""));
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteObjectNegative() throws Exception {
        dao.delete(null);
    }
}