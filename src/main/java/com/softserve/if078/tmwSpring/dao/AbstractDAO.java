package com.softserve.if078.tmwSpring.dao;

import java.util.List;

public interface AbstractDAO<E> {

	List<E> getAll();

	E get(Integer id);

	boolean update(E entity, Integer id);

	boolean delete(Integer id);

	boolean create(E entity);

}
