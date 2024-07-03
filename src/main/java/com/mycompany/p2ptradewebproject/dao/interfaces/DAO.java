package com.mycompany.p2ptradewebproject.dao.interfaces;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(long id);

    List<T> getAll();

    void create(T t);

    void update(T t, String[] params);

    void delete(T t);
}
