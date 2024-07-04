package com.mycompany.p2ptradewebproject.persistence.dao;

import com.mycompany.p2ptradewebproject.persistence.connection.DataSource;
import com.mycompany.p2ptradewebproject.persistence.dao.interfaces.IDAOCurrency;
import com.mycompany.p2ptradewebproject.persistence.entities.CurrencyEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CurrencyDAO implements IDAOCurrency {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getSimpleName());
    private static CurrencyDAO instance = null;

    private static final String INSERT_CURRENCY = "INSERT INTO currency(name, code) VALUES(?,?)";
    private static final String SELECT_CURRENCY = "SELECT * FROM currency WHERE id=?";
    private static final String SELECT_ALL_CURRENCIES = "SELECT * FROM currency";
    private static final String UPDATE_CURRENCY = "UPDATE currency SET name=?, code=? WHERE id=?";
    private static final String DELETE_CURRENCY = "DELETE FROM currency WHERE id=?";

    private DataSource dataSource;


    private CurrencyDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static synchronized CurrencyDAO getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new CurrencyDAO(dataSource);
        }
        return instance;
    }

    @Override
    public Optional<CurrencyEntity> get(long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_CURRENCY)) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToCurrencyEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<CurrencyEntity> getAll() {
        List<CurrencyEntity> result = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_CURRENCIES)
        ) {
            while (resultSet.next()) {
                result.add(mapResultSetToCurrencyEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void create(CurrencyEntity currencyEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_CURRENCY)) {
            ps.setString(1, currencyEntity.getName());
            ps.setString(2, currencyEntity.getCode());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    @Override
    public void delete(CurrencyEntity currencyEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_CURRENCY)) {
            ps.setLong(1, currencyEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private CurrencyEntity mapResultSetToCurrencyEntity(ResultSet resultSet) throws SQLException {
        return new CurrencyEntity(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("code")
        );
    }
}
