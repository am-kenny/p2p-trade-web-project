package com.mycompany.p2ptradewebproject.persistence.jdbc;

import com.mycompany.p2ptradewebproject.persistence.connection.AbstractDataSource;
import com.mycompany.p2ptradewebproject.persistence.connection.DataSource;
import com.mycompany.p2ptradewebproject.persistence.jdbc.interfaces.IDAOUser;
import com.mycompany.p2ptradewebproject.persistence.entities.UserEntity;
import com.mycompany.p2ptradewebproject.persistence.entities.UserVerificationEntity;
import com.mycompany.p2ptradewebproject.persistence.jdbc.mapper.ResultSetMapper;
import com.mycompany.p2ptradewebproject.persistence.jdbc.mapper.UserVerificationMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class UserDAO extends GenericDAO<UserEntity> implements IDAOUser {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getSimpleName());
    private static UserDAO instance = null;
    private AbstractDataSource dataSource;
    private ResultSetMapper<UserEntity> mapper;

    private static final String INSERT_USER = "INSERT INTO user(username, email, password) VALUES(?,?,?)";
    private static final String UPDATE_USER = "UPDATE user SET username=?,email=?,password=? WHERE id=?";
    private static final String DELETE_USER = "DELETE FROM user WHERE id=?";


    private UserDAO(AbstractDataSource dataSource, ResultSetMapper<UserEntity> mapper) {
        super(dataSource, mapper);
        this.dataSource = dataSource;
        this.mapper = mapper;
    }

    public static synchronized UserDAO getInstance(AbstractDataSource dataSource, ResultSetMapper<UserEntity> mapper) {
        if (instance == null) {
            instance = new UserDAO(dataSource, mapper);
        }
        return instance;
    }


    @Override
    public void create(UserEntity userEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, userEntity.getUsername());
            ps.setString(2, userEntity.getEmail());
            ps.setString(3, userEntity.getPassword());
            ps.executeUpdate();
//            ResultSet genKeys = ps.getGeneratedKeys();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void update(UserEntity userEntity, String[] params) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER)) {
            ps.setString(1, params[0] != null ? params[0] : userEntity.getUsername());
            ps.setString(2, params[1] != null ? params[1] : userEntity.getEmail());
            ps.setString(3, params[2] != null ? params[2] : userEntity.getPassword());
            ps.setLong(4, userEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void delete(UserEntity userEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_USER)) {
            ps.setLong(1, userEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public Optional<UserEntity> findByUsernamePassword(String username, String password) {
        return findSingleByDynamicSelect(mapper.getQuerySelectAll() + " WHERE username=? AND password=?", username, password);
    }

}
