package com.mycompany.p2ptradewebproject.persistence.jdbc.factory;

import com.mycompany.p2ptradewebproject.persistence.connection.AbstractDataSource;
import com.mycompany.p2ptradewebproject.persistence.connection.EDatabaseType;
import com.mycompany.p2ptradewebproject.persistence.jdbc.interfaces.*;

import java.util.function.Supplier;

public abstract class AbstractDAOFactory {
    public abstract AbstractDataSource getDataSource();
    public abstract IDAOUser getUserDAO();
    public abstract IDAOUserVerification getUserVerificationDAO();
    public abstract IDAOBankAccount getBankAccountDAO();
    public abstract IDAOBank getBankDAO();
    public abstract IDAOCurrency getCurrencyDAO();
    public abstract IDAOTrade getTradeDAO();
    public abstract IDAOMessage getMessageDAO();
    public abstract IDAOFeedback getFeedbackDAO();
    public abstract <T> T doInTransaction(Supplier<T> operation);

    public static AbstractDAOFactory getMySqlDAOFactory() {
        return MySqlDAOFactory.getInstance(EDatabaseType.MYSQL);
    }
    public static AbstractDAOFactory getPostgreSqlDAOFactory() {
        return null;
    }

    public static AbstractDAOFactory createDAOFactory(EDatabaseType databaseType) {
        return switch (databaseType) {
            case MYSQL, MYSQL_TOMCAT -> MySqlDAOFactory.getInstance(databaseType);
        };
    }

}
