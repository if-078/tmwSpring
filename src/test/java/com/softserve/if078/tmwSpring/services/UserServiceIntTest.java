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
	public void iTShouldInsertUser()
	    throws Exception {
		// Given
		User userTest = new User();
		userTest.setName("userAcademy");
		userTest.setEmail("softServeAcademy@gmail.test");
		userTest.setPass("academypassword");
		// When
		boolean isInsert = userService.create(userTest);
		//Then
		assertEquals(true, isInsert);
	}

	@Test
	public void iTShouldGetAllUsersAndGetOne()
	    throws Exception {
		// Given
		User userFromList;
		User userGetOne;
		// When
		userFromList = userService.getAll().stream().findFirst().get();
		userGetOne = userService.get(userFromList.getId());
		//Then
		assertEquals(userFromList.getId(), userGetOne.getId());
	}

}
