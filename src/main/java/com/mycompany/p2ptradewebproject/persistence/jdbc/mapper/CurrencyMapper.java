package com.mycompany.p2ptradewebproject.persistence.jdbc.mapper;

import com.mycompany.p2ptradewebproject.persistence.entities.CurrencyEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyMapper implements ResultSetMapper<CurrencyEntity> {
    private static final String TABLE_NAME = "currency";
    private static final String MYSQL_QUERY_SELECT_ALL = "SELECT id, name, code FROM " + TABLE_NAME;
    private static final String MYSQL_QUERY_COUNT = "SELECT COUNT(id) FROM " + TABLE_NAME;
    private static final String ID_LABEL = "id";
    private static final String NAME_LABEL = "name";
    private static final String CODE_LABEL = "code";

    private static CurrencyMapper instance;

    private CurrencyMapper() {}

    public static synchronized CurrencyMapper getInstance() {
        if (instance == null) {
            instance = new CurrencyMapper();
        }
        return instance;
    }

    @Override
    public CurrencyEntity map(ResultSet resultSet) throws SQLException {
        CurrencyEntity currency = new CurrencyEntity();
        currency.setId(resultSet.getInt(ID_LABEL));
        currency.setName(resultSet.getString(NAME_LABEL));
        currency.setCode(resultSet.getString(CODE_LABEL));

        return currency;
    }

    @Override
    public String getQuerySelectAll() {
        return MYSQL_QUERY_SELECT_ALL;
    }

    @Override
    public String getQueryCount() {
        return MYSQL_QUERY_COUNT;
    }
}
