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
public class UserServiceIntTest {

	@Autowired
	UserService userService;

	@Test
	public void iTShouldInsertGetAllUsersAndGetOne()
	    throws Exception {
		// Given
		User userNew = new User();
		userNew.setEmail("softServeAcademy@gmail.test");
		userNew.setPass("academypassword");
		User userTest;
		// When
		userService.create(userNew);
		userNew = userService.create(userNew);
		userTest = userService.get(userNew.getId());
		// Then
		assertEquals(userNew.getId(), userTest.getId());
	}

}
