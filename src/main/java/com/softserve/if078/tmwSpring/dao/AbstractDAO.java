package com.softserve.if078.tmwSpring.dao;

import java.util.List;

public interface AbstractDAO<E> {

	List<E> getAll();

	E get(int id);

	void update(E entity);

	void delete(int id);

	void create(E entity);

}
