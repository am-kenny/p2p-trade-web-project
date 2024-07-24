package com.mycompany.p2ptradewebproject.persistence.jdbc.factory;

import com.mycompany.p2ptradewebproject.persistence.connection.AbstractDataSource;
import com.mycompany.p2ptradewebproject.persistence.connection.EDatabaseType;
import com.mycompany.p2ptradewebproject.persistence.jdbc.interfaces.*;

public abstract class AbstractDAOFactory {

    public abstract AbstractDataSource getDataSource();

    public abstract IDAOUser createUserDAO();

    public abstract IDAOUserVerification createUserVerificationDAO();

    public abstract IDAOBankAccount createBankAccountDAO();

    public abstract IDAOBank createBankDAO();

    public abstract IDAOCurrency createCurrencyDAO();

    public abstract IDAOTrade createTradeDAO();

    public abstract IDAOMessage createMessageDAO();

    public abstract IDAOFeedback createFeedbackDAO();

    public static AbstractDAOFactory createDAOFactory(EDatabaseType databaseType) {
        return switch (databaseType) {
            case MYSQL, MYSQL_TOMCAT -> MySqlDAOFactory.getInstance(databaseType);
        };
    }

}
