package com.mycompany.p2ptradewebproject.persistence.jdbc;

import com.mycompany.p2ptradewebproject.persistence.connection.AbstractDataSource;
import com.mycompany.p2ptradewebproject.persistence.connection.DataSource;
import com.mycompany.p2ptradewebproject.persistence.entities.UserEntity;
import com.mycompany.p2ptradewebproject.persistence.jdbc.interfaces.IDAOUserVerification;
import com.mycompany.p2ptradewebproject.persistence.entities.UserVerificationEntity;
import com.mycompany.p2ptradewebproject.persistence.jdbc.mapper.ResultSetMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class UserVerificationDAO extends GenericDAO<UserVerificationEntity> implements IDAOUserVerification {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getSimpleName());
    private static UserVerificationDAO instance = null;
    private AbstractDataSource dataSource;
    private ResultSetMapper<UserVerificationEntity> mapper;

    private static final String INSERT_USER_VERIFICATION = "INSERT INTO user_verification(name, surname, passport_number, passport_photo_reference) VALUES(?,?,?,?)";
    private static final String UPDATE_USER_VERIFICATION = "UPDATE user_verification SET name=?, surname=?, passport_number=?, passport_photo_reference=?, is_banned=? WHERE id=?";
    private static final String DELETE_USER_VERIFICATION = "DELETE FROM user_verification WHERE id=?";


    private UserVerificationDAO(AbstractDataSource dataSource, ResultSetMapper<UserVerificationEntity> mapper) {
        super(dataSource, mapper);
        this.dataSource = dataSource;
        this.mapper = mapper;
    }

    public static synchronized UserVerificationDAO getInstance(AbstractDataSource dataSource, ResultSetMapper<UserVerificationEntity> mapper) {
        if (instance == null) {
            instance = new UserVerificationDAO(dataSource, mapper);
        }
        return instance;
    }


    @Override
    public void create(UserVerificationEntity userVerificationEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER_VERIFICATION)) {
            ps.setString(1, userVerificationEntity.getName());
            ps.setString(2, userVerificationEntity.getSurname());
            ps.setString(3, userVerificationEntity.getPassportNumber());
            ps.setString(4, userVerificationEntity.getPassportPhotoReference());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void update(UserVerificationEntity userVerificationEntity, String[] params) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_VERIFICATION)) {
            ps.setString(1, params[0] != null ? params[0] : userVerificationEntity.getName());
            ps.setString(2, params[1] != null ? params[1] : userVerificationEntity.getSurname());
            ps.setString(3, params[2] != null ? params[2] : userVerificationEntity.getPassportNumber());
            ps.setString(4, params[3] != null ? params[3] : userVerificationEntity.getPassportPhotoReference());
            ps.setBoolean(5, params[4] != null ? Boolean.parseBoolean(params[4]) : userVerificationEntity.getBanned());
            ps.setLong(6, userVerificationEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void delete(UserVerificationEntity userVerificationEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_USER_VERIFICATION)) {
            ps.setLong(1, userVerificationEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }


}
