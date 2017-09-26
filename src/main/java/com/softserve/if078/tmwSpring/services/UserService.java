package com.softserve.if078.tmwSpring.services;

import java.util.List;

import com.softserve.if078.tmwSpring.entities.User;

public interface UserService {

	List<User> getAll();

	User get(Integer id);

	boolean update(User entity, Integer id);

	boolean delete(Integer id);

	boolean create(User entity);

}
