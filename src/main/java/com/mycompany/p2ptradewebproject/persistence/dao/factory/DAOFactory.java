package com.mycompany.p2ptradewebproject.persistence.dao.factory;

import com.mycompany.p2ptradewebproject.persistence.connection.DataSource;
import com.mycompany.p2ptradewebproject.persistence.connection.EDatabaseType;
import com.mycompany.p2ptradewebproject.persistence.dao.interfaces.*;

import java.util.function.Supplier;

public abstract class DAOFactory {
    public abstract DataSource getDataSource();
    public abstract IDAOUser getUserDAO();
    public abstract IDAOUserVerification getUserVerificationDAO();
    public abstract IDAOBankAccount getBankAccountDAO();
    public abstract IDAOBank getBankDAO();
    public abstract IDAOCurrency getCurrencyDAO();
    public abstract IDAOTrade getTradeDAO();
    public abstract IDAOMessage getMessageDAO();
    public abstract IDAOFeedback getFeedbackDAO();
    public abstract <T> T doInTransaction(Supplier<T> operation);

    public static DAOFactory getMySqlDAOFactory() {
        return MySqlDAOFactory.getInstance(EDatabaseType.MYSQL);
    }
    public static DAOFactory getPostgreSqlDAOFactory() {
        return null;
    }

    public static DAOFactory createDAOFactory(EDatabaseType databaseType) {
        return switch (databaseType) {
            case MYSQL -> MySqlDAOFactory.getInstance(databaseType);
        };
    }

}
