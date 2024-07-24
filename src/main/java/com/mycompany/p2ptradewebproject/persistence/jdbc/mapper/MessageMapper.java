package com.mycompany.p2ptradewebproject.persistence.jdbc.mapper;

import com.mycompany.p2ptradewebproject.persistence.entities.MessageEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageMapper implements ResultSetMapper<MessageEntity> {
    private static final String TABLE_NAME = "trade_message";
    private static final String MYSQL_QUERY_SELECT_ALL = "SELECT id, user_id, trade_id, text, media, created_at FROM " + TABLE_NAME;
    private static final String MYSQL_QUERY_COUNT = "SELECT COUNT(id) FROM " + TABLE_NAME;
    private static final String ID_LABEL = "id";
    private static final String USER_ID_LABEL = "user_id";
    private static final String TRADE_ID_LABEL = "trade_id";
    private static final String TEXT_LABEL = "text";
    private static final String MEDIA_LABEL = "media";
    private static final String CREATED_AT_LABEL = "created_at";

    private static MessageMapper instance;

    private MessageMapper() {}

    public static synchronized MessageMapper getInstance() {
        if (instance == null) {
            instance = new MessageMapper();
        }
        return instance;
    }

    @Override
    public MessageEntity map(ResultSet resultSet) throws SQLException {
        MessageEntity message = new MessageEntity();
        message.setId(resultSet.getInt(ID_LABEL));
//        message.setUser(); // TODO
//        message.setTrade();
        message.setText(resultSet.getString(TEXT_LABEL));
        message.setMedia(resultSet.getString(MEDIA_LABEL));
        message.setCreatedAt(resultSet.getTimestamp(CREATED_AT_LABEL));

        return message;
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
