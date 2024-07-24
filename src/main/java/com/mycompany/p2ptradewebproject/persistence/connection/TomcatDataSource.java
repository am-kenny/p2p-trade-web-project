package com.mycompany.p2ptradewebproject.persistence.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class TomcatDataSource extends AbstractDataSource{

    private static final Logger LOGGER = Logger.getLogger(TomcatDataSource.class.getSimpleName());
    private static TomcatDataSource instance;
    private DataSource ds;
    private Connection connection;

    private TomcatDataSource(EDatabaseType databaseType) {
        ResourceBundle resource = ResourceBundle.getBundle(databaseType.getPropertiesFile());
        String name = resource.getString("name");
        try {
            Context initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup("java:comp/env");
            ds = (DataSource) envContext.lookup(name);
        } catch (NamingException e) {
            LOGGER.severe("Error during connection creation: " + e.getMessage());
            throw new RuntimeException("Error during connection creation. Please, check credentials. ", e);
        }
    }

    public static synchronized TomcatDataSource getInstance(EDatabaseType databaseType) {
        if (instance == null) {
            instance = new TomcatDataSource(databaseType);
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = ds.getConnection();

            }
        } catch (SQLException e) {
            LOGGER.severe("Error during connection creation: " + e.getMessage());
            throw new RuntimeException("Error during connection creation. Please, check credentials. ", e);
        }
        return connection;
    }

    @Override
    public <T> T doInTransaction(Supplier<T> operation) {
        T result;
        try {
            connection = getConnection();
            try {
                connection.setAutoCommit(false);
                result = operation.get();
                connection.commit();
            } catch (Exception ex) {
                connection.rollback();
                throw new RuntimeException("Error during transaction. Transaction is rollbacked. ", ex);
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Error during transaction: " + e.getMessage());
            throw new RuntimeException("Error during transaction. Please check connection. ", e);
        }
        return result;
    }
}
