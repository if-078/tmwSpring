package com.softserve.if078.tmwSpring.dao;

import java.sql.SQLException;
import java.util.List;

public interface DaoInterface<E> {

	List<E> getAll() throws SQLException;

	E findOne(int id) throws SQLException;

	boolean update(E entity)throws SQLException;

	boolean delete(int id)throws SQLException;

	E create(E entity)throws SQLException;

}
