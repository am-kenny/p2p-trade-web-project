package com.mycompany.p2ptradewebproject.persistence.jdbc.mapper;

import com.mycompany.p2ptradewebproject.persistence.entities.UserVerificationEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserVerificationMapper implements ResultSetMapper<UserVerificationEntity> {
    private static final String TABLE_NAME = "user_verification";
    private static final String MYSQL_QUERY_SELECT_ALL = "SELECT id, name, surname, passport_number, passport_photo_reference, is_banned FROM " + TABLE_NAME;
    private static final String MYSQL_QUERY_COUNT = "SELECT COUNT(id) FROM " + TABLE_NAME;
    private static final String ID_LABEL = "id";
    private static final String NAME_LABEL = "name";
    private static final String SURNAME_LABEL = "surname";
    private static final String PASSPORT_NUMBER_LABEL = "passport_number";
    private static final String PASSPORT_PHOTO_REFERENCE_LABEL = "passport_photo_reference";
    private static final String IS_BANNED_LABEL = "is_banned";

    private static UserVerificationMapper instance;

    private UserVerificationMapper() {}

    public static synchronized UserVerificationMapper getInstance() {
        if (instance == null) {
            instance = new UserVerificationMapper();
        }
        return instance;
    }

    @Override
    public UserVerificationEntity map(ResultSet resultSet) throws SQLException {
        UserVerificationEntity userVerification = new UserVerificationEntity();
        userVerification.setId(resultSet.getInt(ID_LABEL));
        userVerification.setName(resultSet.getString(NAME_LABEL));
        userVerification.setSurname(resultSet.getString(SURNAME_LABEL));
        userVerification.setPassportNumber(resultSet.getString(PASSPORT_NUMBER_LABEL));
        userVerification.setPassportPhotoReference(resultSet.getString(PASSPORT_PHOTO_REFERENCE_LABEL));
        userVerification.setBanned(resultSet.getBoolean(IS_BANNED_LABEL));

        return userVerification;
    }

    @Override
    public String getQuerySelectAll() {
        return MYSQL_QUERY_SELECT_ALL;
    }

    @Override
    public String getQueryCount() {
        return MYSQL_QUERY_COUNT;
    }
}
