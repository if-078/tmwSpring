package com.softserve.if078.tmwSpring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	/*@Autowired
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
	void updateUser(@RequestBody User user) {
		userService.update(user);
	}

	@DeleteMapping("/{userid}")
	void deleteUser(@PathVariable Integer userid) {
		userService.delete();
	}
*/
}
