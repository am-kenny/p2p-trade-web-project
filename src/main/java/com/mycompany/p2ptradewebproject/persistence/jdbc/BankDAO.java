package com.mycompany.p2ptradewebproject.persistence.jdbc;

import com.mycompany.p2ptradewebproject.persistence.connection.AbstractDataSource;
import com.mycompany.p2ptradewebproject.persistence.connection.DataSource;
import com.mycompany.p2ptradewebproject.persistence.entities.UserEntity;
import com.mycompany.p2ptradewebproject.persistence.jdbc.interfaces.IDAOBank;
import com.mycompany.p2ptradewebproject.persistence.entities.BankEntity;
import com.mycompany.p2ptradewebproject.persistence.jdbc.mapper.ResultSetMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class BankDAO extends GenericDAO<BankEntity> implements IDAOBank {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getSimpleName());
    private static BankDAO instance = null;
    private AbstractDataSource dataSource;
    private ResultSetMapper<BankEntity> mapper;

    private static final String INSERT_BANK = "INSERT INTO bank(name) VALUES(?)";
    private static final String UPDATE_BANK = "UPDATE bank SET name=? WHERE id=?";
    private static final String DELETE_BANK = "DELETE FROM bank WHERE id=?";


    private BankDAO(AbstractDataSource dataSource, ResultSetMapper<BankEntity> mapper) {
        super(dataSource, mapper);
        this.dataSource = dataSource;
        this.mapper = mapper;
    }

    public static synchronized BankDAO getInstance(AbstractDataSource dataSource, ResultSetMapper<BankEntity> mapper) {
        if (instance == null) {
            instance = new BankDAO(dataSource, mapper);
        }
        return instance;
    }


    @Override
    public void create(BankEntity bankEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_BANK)) {
            ps.setString(1, bankEntity.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void update(BankEntity bankEntity, String[] params) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_BANK)) {
            ps.setString(1, params[0] != null ? params[0] : bankEntity.getName());
            ps.setLong(3, bankEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void delete(BankEntity bankEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_BANK)) {
            ps.setLong(1, bankEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

}
