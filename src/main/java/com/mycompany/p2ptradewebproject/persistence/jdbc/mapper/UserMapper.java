package com.mycompany.p2ptradewebproject.persistence.jdbc.mapper;
import com.mycompany.p2ptradewebproject.persistence.entities.UserEntity;
import com.mycompany.p2ptradewebproject.persistence.entities.UserVerificationEntity;
import com.mycompany.p2ptradewebproject.persistence.jdbc.UserVerificationDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserMapper implements ResultSetMapper<UserEntity> {
//  columns:   id username email password user_verification_id created_at
    private final String MYSQL_QUERY_SELECT_ALL ="SELECT id as a1, username as a2, email as a3, password as a4, user_verification_id as a5, created_at as a6 FROM user";
    private final String MYSQL_QUERY_COUNT = "SELECT COUNT(id) FROM user";
    private static final String ID_LABEL = "id";
    private static final String USERNAME_LABEL = "username";
    private static final String EMAIL_LABEL = "email";
    private static final String PASSWORD_LABEL = "password";
    private static final String USER_VERIFICATION_ID_LABEL = "user_verification_id";

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

        return user;
    }

    @Override
    public String getQuerySelectAll() {
        return MYSQL_QUERY_SELECT_ALL;
    }

    public String getQueryCount() {
        return MYSQL_QUERY_COUNT;
    }
}
