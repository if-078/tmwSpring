package com.softserve.if078.tmwSpring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.if078.tmwSpring.entities.User;
import com.softserve.if078.tmwSpring.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/")
	List<User> getAllUsers() {
		return userService.getAll();
	}

	@PostMapping("/")
	void createUser(@RequestBody User user) {
		userService.create(user);
	}

	@GetMapping("/{userid}")
	User getUser(@PathVariable Integer userid) {
		return userService.get(userid);
	}

	@PutMapping("/{userid}")
	void updateUser(@RequestBody User user, @PathVariable Integer userid) {
		userService.update(user, userid);
	}

	@DeleteMapping("/{userid}")
	void deleteUser(@PathVariable Integer userid) {
		userService.delete(userid);
	}

}
