package com.mycompany.p2ptradewebproject.dao;

import com.mycompany.p2ptradewebproject.connection.DataSource;
import com.mycompany.p2ptradewebproject.dao.interfaces.IDAOBank;
import com.mycompany.p2ptradewebproject.entities.BankEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class BankDAO implements IDAOBank {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getSimpleName());
    private static BankDAO instance = null;

    private static final String INSERT_BANK = "INSERT INTO bank(name) VALUES(?)";
    private static final String SELECT_BANK = "SELECT * FROM bank WHERE id=?";
    private static final String SELECT_ALL_BANKS = "SELECT * FROM bank";
    private static final String UPDATE_BANK = "UPDATE bank SET name=? WHERE id=?";
    private static final String DELETE_BANK = "DELETE FROM bank WHERE id=?";

    private DataSource dataSource;


    private BankDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static synchronized BankDAO getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new BankDAO(dataSource);
        }
        return instance;
    }

    @Override
    public Optional<BankEntity> get(long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BANK)) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToBankEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<BankEntity> getAll() {
        List<BankEntity> result = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_BANKS)
        ) {
            while (resultSet.next()) {
                result.add(mapResultSetToBankEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void create(BankEntity bankEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_BANK)) {
            ps.setString(1, bankEntity.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    @Override
    public void delete(BankEntity bankEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_BANK)) {
            ps.setLong(1, bankEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private BankEntity mapResultSetToBankEntity(ResultSet resultSet) throws SQLException {
        return new BankEntity(
                resultSet.getInt("id"),
                resultSet.getString("name")
        );
    }
}
