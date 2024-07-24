package com.mycompany.p2ptradewebproject.persistence.jdbc;

import com.mycompany.p2ptradewebproject.persistence.connection.AbstractDataSource;
import com.mycompany.p2ptradewebproject.persistence.connection.DataSource;
import com.mycompany.p2ptradewebproject.persistence.jdbc.interfaces.IDAOUser;
import com.mycompany.p2ptradewebproject.persistence.entities.UserEntity;
import com.mycompany.p2ptradewebproject.persistence.entities.UserVerificationEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class UserDAO implements IDAOUser {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getSimpleName());
    private static UserDAO instance = null;

    private static final String ID_LABEL = "id";
    private static final String USERNAME_LABEL = "username";
    private static final String EMAIL_LABEL = "email";
    private static final String PASSWORD_LABEL = "password";
    private static final String USER_VERIFICATION_ID_LABEL = "user_verification_id";

    private static final String INSERT_USER = "INSERT INTO user(username, email, password) VALUES(?,?,?)";
    private static final String SELECT_USER = "SELECT * FROM user WHERE id=?";
    private static final String SELECT_USER_BY_USERNAME_PASSWORD = "SELECT * FROM user WHERE username=? AND password=?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM user";
    private static final String UPDATE_USER = "UPDATE user SET username=?,email=?,password=? WHERE id=?";
    private static final String DELETE_USER = "DELETE FROM user WHERE id=?";

    private AbstractDataSource dataSource;


    private UserDAO(AbstractDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static synchronized UserDAO getInstance(AbstractDataSource dataSource) {
        if (instance == null) {
            instance = new UserDAO(dataSource);
        }
        return instance;
    }


    @Override
    public Optional<UserEntity> findById(long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER)) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {

                if (resultSet.next()) {
                    return Optional.of(mapResultSetToUserEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserEntity> findByUsernamePassword(String username, String password) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_USERNAME_PASSWORD)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet resultSet = ps.executeQuery()) {

                if (resultSet.next()) {
                    return Optional.of(mapResultSetToUserEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> result = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS)) {
            while (resultSet.next()) {
                result.add(mapResultSetToUserEntity(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
        return result;
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

    private UserEntity mapResultSetToUserEntity(ResultSet resultSet) throws SQLException {
        Optional<UserVerificationEntity> userVerificationEntityOptional = UserVerificationDAO.getInstance(dataSource).findById(resultSet.getInt(USER_VERIFICATION_ID_LABEL));

        return new UserEntity(
                resultSet.getInt(ID_LABEL),
                resultSet.getString(USERNAME_LABEL),
                resultSet.getString(EMAIL_LABEL),
                resultSet.getString(PASSWORD_LABEL),
//                resultSet.getMetaData().isNullable()
                userVerificationEntityOptional.orElse(null)
        );
    }
}
