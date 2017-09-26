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
	public boolean update(User entity, Integer id) {
		return userDao.update(entity, id);
	}

	@Override
	public boolean delete(Integer id) {
		return userDao.delete(id);
	}

	@Override
	public boolean create(User entity) {
		return userDao.create(entity);
	}

}
