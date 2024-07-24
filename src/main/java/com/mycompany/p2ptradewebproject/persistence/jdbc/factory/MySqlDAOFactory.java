package com.mycompany.p2ptradewebproject.persistence.jdbc.factory;

import com.mycompany.p2ptradewebproject.persistence.connection.AbstractDataSource;
import com.mycompany.p2ptradewebproject.persistence.connection.DataSource;
import com.mycompany.p2ptradewebproject.persistence.connection.EDatabaseType;
import com.mycompany.p2ptradewebproject.persistence.connection.TomcatDataSource;
import com.mycompany.p2ptradewebproject.persistence.jdbc.*;
import com.mycompany.p2ptradewebproject.persistence.jdbc.mapper.*;

import java.util.Objects;
import java.util.function.Supplier;

public class MySqlDAOFactory extends AbstractDAOFactory {

    private static MySqlDAOFactory instance = null;
    private AbstractDataSource dataSource;


    private MySqlDAOFactory(EDatabaseType databaseType) {
        this.dataSource = switch (databaseType) {
            case MYSQL -> DataSource.getInstance(databaseType);
            case MYSQL_TOMCAT -> TomcatDataSource.getInstance(databaseType);
        };
    }

    public static synchronized MySqlDAOFactory getInstance(EDatabaseType databaseType) {
        if (Objects.isNull(instance)) {
            instance = new MySqlDAOFactory(databaseType);
        }
        return instance;
    }

    @Override
    public AbstractDataSource getDataSource() {
        return dataSource;
    }

    @Override
    public UserDAO createUserDAO() {
        return UserDAO.getInstance(dataSource, UserMapper.getInstance());
    }

    @Override
    public UserVerificationDAO createUserVerificationDAO() {
        return UserVerificationDAO.getInstance(dataSource, UserVerificationMapper.getInstance());
    }

    @Override
    public BankAccountDAO createBankAccountDAO() {
        return BankAccountDAO.getInstance(dataSource, BankAccountMapper.getInstance());
    }

    @Override
    public BankDAO createBankDAO() {
        return BankDAO.getInstance(dataSource, BankMapper.getInstance());
    }

    @Override
    public CurrencyDAO createCurrencyDAO() {
        return CurrencyDAO.getInstance(dataSource, CurrencyMapper.getInstance());
    }

    @Override
    public TradeDAO createTradeDAO() {
        return TradeDAO.getInstance(dataSource, TradeMapper.getInstance());
    }

    @Override
    public MessageDAO createMessageDAO() {
        return MessageDAO.getInstance(dataSource, MessageMapper.getInstance());
    }

    @Override
    public FeedbackDAO createFeedbackDAO() {
        return FeedbackDAO.getInstance(dataSource, FeedbackMapper.getInstance());
    }
}
