package com.mycompany.p2ptradewebproject.dao.factory;

import com.mycompany.p2ptradewebproject.connection.DataSource;
import com.mycompany.p2ptradewebproject.connection.EDatabaseType;
import com.mycompany.p2ptradewebproject.dao.*;

import java.util.Objects;
import java.util.function.Supplier;

public class MySqlDAOFactory extends DAOFactory {
    private static MySqlDAOFactory instance = null;
    private DataSource dataSource;


    private MySqlDAOFactory(EDatabaseType databaseType) {
        this.dataSource = DataSource.getInstance(databaseType);
    }

    public static synchronized MySqlDAOFactory getInstance(EDatabaseType databaseType) {
        if (Objects.isNull(instance)) {
            instance = new MySqlDAOFactory(databaseType);
        }
        return instance;
    }

    public <T> T doInTransaction(Supplier<T> operation) {
        return dataSource.<T>doInTransaction(operation);
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public UserDAO getUserDAO() {
        return UserDAO.getInstance(dataSource);
    }

    @Override
    public UserVerificationDAO getUserVerificationDAO() {
        return UserVerificationDAO.getInstance(dataSource);
    }

    @Override
    public BankAccountDAO getBankAccountDAO() {
        return BankAccountDAO.getInstance(dataSource);
    }

    @Override
    public BankDAO getBankDAO() {
        return BankDAO.getInstance(dataSource);
    }

    @Override
    public CurrencyDAO getCurrencyDAO() {
        return CurrencyDAO.getInstance(dataSource);
    }

    @Override
    public TradeDAO getTradeDAO() {
        return TradeDAO.getInstance(dataSource);
    }

    @Override
    public MessageDAO getMessageDAO() {
        return MessageDAO.getInstance(dataSource);
    }

    @Override
    public FeedbackDAO getFeedbackDAO() {
        return FeedbackDAO.getInstance(dataSource);
    }
}
