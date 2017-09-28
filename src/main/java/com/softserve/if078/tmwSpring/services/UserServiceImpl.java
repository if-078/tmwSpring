package com.softserve.if078.tmwSpring.services;

import java.sql.SQLException;
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
	public boolean update(User entity)throws SQLException {
		return userDao.update(entity);
	}

	@Override
	public boolean delete(Integer id)throws SQLException {
		return userDao.delete(id);
		
	}

	@Override
	public User create(User entity)throws SQLException {
		return userDao.create(entity);
		
	}
}
