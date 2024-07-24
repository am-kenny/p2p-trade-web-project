package com.mycompany.p2ptradewebproject.persistence.jdbc;

import com.mycompany.p2ptradewebproject.persistence.connection.AbstractDataSource;
import com.mycompany.p2ptradewebproject.persistence.connection.DataSource;
import com.mycompany.p2ptradewebproject.persistence.entities.BankEntity;
import com.mycompany.p2ptradewebproject.persistence.entities.UserEntity;
import com.mycompany.p2ptradewebproject.persistence.jdbc.interfaces.IDAOCurrency;
import com.mycompany.p2ptradewebproject.persistence.entities.CurrencyEntity;
import com.mycompany.p2ptradewebproject.persistence.jdbc.mapper.ResultSetMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CurrencyDAO extends GenericDAO<CurrencyEntity> implements IDAOCurrency {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getSimpleName());
    private static CurrencyDAO instance = null;
    private AbstractDataSource dataSource;
    private ResultSetMapper<CurrencyEntity> mapper;

    private static final String INSERT_CURRENCY = "INSERT INTO currency(name, code) VALUES(?,?)";
    private static final String UPDATE_CURRENCY = "UPDATE currency SET name=?, code=? WHERE id=?";
    private static final String DELETE_CURRENCY = "DELETE FROM currency WHERE id=?";


    private CurrencyDAO(AbstractDataSource dataSource, ResultSetMapper<CurrencyEntity> mapper) {
        super(dataSource, mapper);
        this.dataSource = dataSource;
        this.mapper = mapper;
    }

    public static synchronized CurrencyDAO getInstance(AbstractDataSource dataSource, ResultSetMapper<CurrencyEntity> mapper) {
        if (instance == null) {
            instance = new CurrencyDAO(dataSource, mapper);
        }
        return instance;
    }


    @Override
    public void create(CurrencyEntity currencyEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_CURRENCY)) {
            ps.setString(1, currencyEntity.getName());
            ps.setString(2, currencyEntity.getCode());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void update(CurrencyEntity currencyEntity, String[] params) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_CURRENCY)) {
            ps.setString(1, params[0] != null ? params[0] : currencyEntity.getName());
            ps.setString(2, params[1] != null ? params[1] : currencyEntity.getCode());
            ps.setLong(3, currencyEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void delete(CurrencyEntity currencyEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_CURRENCY)) {
            ps.setLong(1, currencyEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
