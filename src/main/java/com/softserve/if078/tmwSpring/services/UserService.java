package com.softserve.if078.tmwSpring.services;

import java.util.List;

import com.softserve.if078.tmwSpring.entities.User;

public interface UserService {

	List<User> getAll();

	User get(Integer id);

	void update(User entity, Integer id);

	void delete(Integer id);

	void create(User entity);

}
