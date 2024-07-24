package com.mycompany.p2ptradewebproject.persistence.jdbc.mapper;
import com.mycompany.p2ptradewebproject.persistence.entities.UserEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<UserEntity> {
    private static final String TABLE_NAME = "user";
    private static final String MYSQL_QUERY_SELECT_ALL = "SELECT id, username, email, password, user_verification_id, created_at FROM " + TABLE_NAME;
    private static final String MYSQL_QUERY_COUNT = "SELECT COUNT(id) FROM  " + TABLE_NAME;
    private static final String ID_LABEL = "id";
    private static final String USERNAME_LABEL = "username";
    private static final String EMAIL_LABEL = "email";
    private static final String PASSWORD_LABEL = "password";
    private static final String USER_VERIFICATION_ID_LABEL = "user_verification_id";
    private static final String CREATED_AT_LABEL = "created_at";

    private static UserMapper instance;

    private UserMapper() {}

    public static synchronized UserMapper getInstance() {
        if (instance == null) {
            instance = new UserMapper();
        }
        return instance;
    }

    @Override
    public UserEntity map(ResultSet resultSet) throws SQLException {
        UserEntity user = new UserEntity();
        user.setId(resultSet.getInt(ID_LABEL));
        user.setUsername(resultSet.getString(USERNAME_LABEL));
        user.setEmail(resultSet.getString(EMAIL_LABEL));
        user.setPassword(resultSet.getString(PASSWORD_LABEL));
        user.setUserVerificationId(resultSet.getInt(USER_VERIFICATION_ID_LABEL));
        user.setCreatedAt(resultSet.getTimestamp(CREATED_AT_LABEL));

        return user;
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
