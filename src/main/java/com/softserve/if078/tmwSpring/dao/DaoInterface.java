package com.softserve.if078.tmwSpring.dao;

import java.util.List;

public interface DaoInterface<E> {

    List<E> getAll();

    E get(E entity);

    void update(E entity);

    void delete(E entity);

    void create(E entity);

}
