package com.mycompany.p2ptradewebproject.persistence.jdbc.mapper;

import com.mycompany.p2ptradewebproject.persistence.entities.BankAccountEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountMapper implements ResultSetMapper<BankAccountEntity> {
    private static final String TABLE_NAME = "bank_account";
    private static final String MYSQL_QUERY_SELECT_ALL = "SELECT id, card_number, bank_id, user_id, currency_id, cardholder_name FROM " + TABLE_NAME;
    private static final String MYSQL_QUERY_COUNT = "SELECT COUNT(id) FROM " + TABLE_NAME;
    private static final String ID_LABEL = "id";
    private static final String CARD_NUMBER_LABEL = "card_number";
    private static final String BANK_ID_LABEL = "bank_id";
    private static final String USER_ID_LABEL = "user_id";
    private static final String CURRENCY_ID_LABEL = "currency_id";
    private static final String CARD_HOLDER_NAME_LABEL = "cardholder_name";

    private static BankAccountMapper instance;

    private BankAccountMapper() {}

    public static synchronized BankAccountMapper getInstance() {
        if (instance == null) {
            instance = new BankAccountMapper();
        }
        return instance;
    }

    @Override
    public BankAccountEntity map(ResultSet resultSet) throws SQLException {
        BankAccountEntity bankAccount = new BankAccountEntity();
        bankAccount.setId(resultSet.getInt(ID_LABEL));
        bankAccount.setCardNumber(resultSet.getInt(CARD_NUMBER_LABEL));
//        bankAccount.setBank(); // TODO
//        bankAccount.setUser();
//        bankAccount.setCurrency();
        bankAccount.setCardholderName(resultSet.getString(CARD_HOLDER_NAME_LABEL));

        return bankAccount;
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
