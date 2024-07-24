package com.mycompany.p2ptradewebproject.persistence.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetMapper<T> {
    T map(ResultSet resultSet) throws SQLException;

    String getQuerySelectAll();
    String getQueryCount();
}
