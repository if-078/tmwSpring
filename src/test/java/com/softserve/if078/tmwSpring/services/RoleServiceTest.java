package com.softserve.if078.tmwSpring.services;

import com.softserve.if078.tmwSpring.entities.Role;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.softserve.if078.tmwSpring.TmwSpringApplicationTests;
import com.softserve.if078.tmwSpring.configurations.H2DbConfig;

import java.util.List;


@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.softserve.if078.tmwSpring")
@SpringBootTest(classes = { TmwSpringApplicationTests.class, H2DbConfig.class })
@EnableConfigurationProperties
public class RoleServiceTest {

    @Autowired
    RoleService roleService;

//    @After
//    public void dropTable() throws Exception {
//        roleService.drop();
//    }


    @Test
    public void testCreateAndUpdateAndGet() throws Exception {
        Role role = new Role("asdf");
        role = roleService.create(role);
        assertEquals("asdf", roleService.get(role.getId()).getName());
        role.setName("fdsa");
        roleService.update(role);
        assertEquals("fdsa", roleService.get(role.getId()).getName());
    }

    @Test
    public void testCreateAndGetAndDelete() throws Exception {
        Role role = new Role("asdf");
        role = roleService.create(role);
        assertNotNull(roleService.get(role.getId()));
        roleService.delete(role.getId());
        assertNull(roleService.get(role.getId()));
    }

    @Test
    public void testCreateAndGetAllAndDeleteAll() throws Exception {
        List<Role> list = roleService.create(new Role("zxcv1"), new Role("zxcv2"), new Role("zxcv3"));
        assertEquals(3, list.size());
        int id = list.get(0).getId();
        for (Role role : roleService.getAll()) {
            if (role.getId() == id) assertEquals("zxcv" + id++, role.getName());
        }
        roleService.deleteAll();
        assertEquals(0, roleService.getAll().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateArgumentNegative() throws Exception {
        roleService.create(new Role(-1, "a"));
    }

    @Test(expected = NullPointerException.class)
    public void testCreateObjectNegative() throws Exception {
        roleService.create(new Role());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetArgumentNegative() throws Exception {
        roleService.get(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateArgumentNegative() throws Exception {
        roleService.update(new Role(-1, "a"));
        roleService.update(new Role(""));
        roleService.update(new Role(null));
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateObjectNegative() throws Exception {
        roleService.update(new Role());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteArgumentNegative() throws Exception {
        roleService.delete(-5);
    }

}