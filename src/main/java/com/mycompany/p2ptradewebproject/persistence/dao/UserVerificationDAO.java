package com.mycompany.p2ptradewebproject.persistence.dao;

import com.mycompany.p2ptradewebproject.persistence.connection.DataSource;
import com.mycompany.p2ptradewebproject.persistence.dao.interfaces.IDAOUserVerification;
import com.mycompany.p2ptradewebproject.persistence.entities.UserVerificationEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class UserVerificationDAO implements IDAOUserVerification {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getSimpleName());
    private static UserVerificationDAO instance = null;

    private static final String ID_LABEL = "id";
    private static final String NAME_LABEL = "name";
    private static final String SURNAME_LABEL = "surname";
    private static final String PASSPORT_NUMBER_LABEL = "passport_number";
    private static final String PASSPORT_PHOTO_LABEL = "passport_photo_reference";
    private static final String IS_BANNED_LABEL = "is_banned";


    private static final String INSERT_USER_VERIFICATION = "INSERT INTO user_verification(name, surname, passport_number, passport_photo_reference) VALUES(?,?,?,?)";
    private static final String SELECT_USER_VERIFICATION = "SELECT * FROM user_verification WHERE id=?";
    private static final String UPDATE_USER_VERIFICATION = "UPDATE user_verification SET name=?, surname=?, passport_number=?, passport_photo_reference=?, is_banned=? WHERE id=?";
    private static final String DELETE_USER_VERIFICATION = "DELETE FROM user_verification WHERE id=?";

    private DataSource dataSource;


    private UserVerificationDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static synchronized UserVerificationDAO getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new UserVerificationDAO(dataSource);
        }
        return instance;
    }


    @Override
    public Optional<UserVerificationEntity> get(long id) {

        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_USER_VERIFICATION)) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    UserVerificationEntity userVerificationEntity = new UserVerificationEntity(
                            resultSet.getInt(ID_LABEL),
                            resultSet.getString(NAME_LABEL),
                            resultSet.getString(SURNAME_LABEL),
                            resultSet.getString(PASSPORT_NUMBER_LABEL),
                            resultSet.getString(PASSPORT_PHOTO_LABEL),
                            resultSet.getBoolean(IS_BANNED_LABEL)
                    );

                    return Optional.of(userVerificationEntity);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<UserVerificationEntity> getAll() {
        List<UserVerificationEntity> result = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM user_verification")
        ) {
            while (resultSet.next()) {
                UserVerificationEntity userVerificationEntity = new UserVerificationEntity(
                        resultSet.getInt(ID_LABEL),
                        resultSet.getString(NAME_LABEL),
                        resultSet.getString(SURNAME_LABEL),
                        resultSet.getString(PASSPORT_NUMBER_LABEL),
                        resultSet.getString(PASSPORT_PHOTO_LABEL),
                        resultSet.getBoolean(IS_BANNED_LABEL)
                );
                result.add(userVerificationEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UserVerificationEntity userVerificationEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_USER_VERIFICATION)) {
            ps.setLong(1, userVerificationEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private UserVerificationEntity mapResultSetToUserVerificationEntity(ResultSet resultSet) throws SQLException {
        return new UserVerificationEntity(
                resultSet.getInt(ID_LABEL),
                resultSet.getString(NAME_LABEL),
                resultSet.getString(SURNAME_LABEL),
                resultSet.getString(PASSPORT_NUMBER_LABEL),
                resultSet.getString(PASSPORT_PHOTO_LABEL),
                resultSet.getBoolean(IS_BANNED_LABEL)
        );
    }
}
