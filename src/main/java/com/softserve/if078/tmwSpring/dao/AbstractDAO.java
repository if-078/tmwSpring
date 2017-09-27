package com.softserve.if078.tmwSpring.dao;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDAO<E> {

	List<E> getAll() throws SQLException;

	E get(int id) throws SQLException;

	void update(E entity) throws SQLException;

	void delete(int id) throws SQLException;

	void create(E entity) throws SQLException;

}