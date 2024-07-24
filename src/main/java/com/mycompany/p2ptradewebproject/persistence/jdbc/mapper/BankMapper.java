package com.mycompany.p2ptradewebproject.persistence.jdbc.mapper;

import com.mycompany.p2ptradewebproject.persistence.entities.BankEntity;

import javax.swing.tree.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankMapper implements ResultSetMapper<BankEntity> {
    private static final String TABLE_NAME = "bank";
    private static final String MYSQL_QUERY_SELECT_ALL = "SELECT id, name FROM " + TABLE_NAME;
    private static final String MYSQL_QUERY_COUNT = "SELECT COUNT(id) FROM " + TABLE_NAME;
    private static final String ID_LABEL = "id";
    private static final String NAME_LABEL = "name";

    private static BankMapper instance;

    private BankMapper() {}

    public static synchronized BankMapper getInstance() {
        if (instance == null) {
            instance = new BankMapper();
        }
        return instance;
    }

    @Override
    public BankEntity map(ResultSet resultSet) throws SQLException {
        BankEntity bank = new BankEntity();
        bank.setId(resultSet.getInt(ID_LABEL));
        bank.setName(resultSet.getString(NAME_LABEL));

        return bank;
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
