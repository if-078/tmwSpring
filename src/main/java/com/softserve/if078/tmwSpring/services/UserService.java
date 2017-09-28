package com.softserve.if078.tmwSpring.services;

import java.util.List;

import com.softserve.if078.tmwSpring.entities.User;

public interface UserService {

	List<User> getAll();

	User get(User entity);

	void update(User entity);

	void delete(User entity);

	void create(User entity);

}
