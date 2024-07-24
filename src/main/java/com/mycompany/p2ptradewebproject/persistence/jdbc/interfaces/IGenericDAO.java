package com.mycompany.p2ptradewebproject.persistence.jdbc.interfaces;

import java.util.List;
import java.util.Optional;

public interface IGenericDAO<T> {

    Optional<T> findById(long id);

    List<T> findAll();

    void create(T t);

    void update(T t, String[] params);

    void delete(T t);
}
