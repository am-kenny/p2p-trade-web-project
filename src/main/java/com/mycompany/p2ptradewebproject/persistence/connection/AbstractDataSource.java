package com.mycompany.p2ptradewebproject.persistence.connection;

import java.sql.Connection;
import java.util.function.Supplier;

public abstract class AbstractDataSource {
    public abstract Connection getConnection();
    public abstract <T> T doInTransaction(Supplier<T> operation);
}
