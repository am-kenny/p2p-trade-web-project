package com.mycompany.p2ptradewebproject.persistence.jdbc.mapper;

import com.mycompany.p2ptradewebproject.persistence.entities.FeedbackEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackMapper implements ResultSetMapper<FeedbackEntity> {
    private static final String TABLE_NAME = "trade_feedback";
    private static final String MYSQL_QUERY_SELECT_ALL = "SELECT id, author_user_id, trade_id, is_positive, text FROM " + TABLE_NAME;
    private static final String MYSQL_QUERY_COUNT = "SELECT COUNT(id) FROM " + TABLE_NAME;
    private static final String ID_LABEL = "id";
    private static final String AUTHOR_USER_ID_LABEL = "author_user_id";
    private static final String TRADE_ID_LABEL = "trade_id";
    private static final String IS_POSITIVE_LABEL = "is_positive";
    private static final String TEXT = "text";

    private static FeedbackMapper instance;

    private FeedbackMapper() {}

    public static synchronized FeedbackMapper getInstance() {
        if (instance == null) {
            instance = new FeedbackMapper();
        }
        return instance;
    }

    @Override
    public FeedbackEntity map(ResultSet resultSet) throws SQLException {
        FeedbackEntity feedback = new FeedbackEntity();
        feedback.setId(resultSet.getInt(ID_LABEL));
//        feedback.setAuthor(); // TODO
//        feedback.setTrade();
        feedback.setIsPositive(resultSet.getBoolean(IS_POSITIVE_LABEL));
        feedback.setText(resultSet.getString(TEXT));

        return feedback;
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
