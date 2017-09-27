package com.softserve.if078.tmwSpring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.if078.tmwSpring.dao.UserDaoImpl;
import com.softserve.if078.tmwSpring.entities.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDaoImpl userDao;

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public User get(Integer id) {
		return userDao.get(id);
	}

	@Override
	public void update(User entity, Integer id) {
		 userDao.update(entity);
	}

	@Override
	public void delete(Integer id) {
		 userDao.delete(id);
	}

	@Override
	public void create(User entity) {
	  userDao.create(entity);
	}

}
