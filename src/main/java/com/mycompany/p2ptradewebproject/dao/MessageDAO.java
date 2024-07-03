package com.mycompany.p2ptradewebproject.dao;

import com.mycompany.p2ptradewebproject.connection.DataSource;
import com.mycompany.p2ptradewebproject.dao.interfaces.IDAOMessage;
import com.mycompany.p2ptradewebproject.entities.MessageEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class MessageDAO implements IDAOMessage {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getSimpleName());
    private static MessageDAO instance = null;

    private static final String ID_LABEL = "id";
    private static final String USER_ID_LABEL = "user_id";
    private static final String TRADE_ID_LABEL = "trade_id";
    private static final String TEXT_LABEL = "text";
    private static final String MEDIA_LABEL = "media";
    private static final String CREATED_AT_LABEL = "created_at";

    private static final String INSERT_MESSAGE = "INSERT INTO trade_message(user_id, trade_id, text, media) VALUES(?,?,?,?)";
    private static final String SELECT_ALL_MESSAGES = "SELECT * FROM trade_message";
    private static final String SELECT_MESSAGE = "SELECT * FROM trade_message WHERE id=?";
    private static final String UPDATE_MESSAGE = "UPDATE trade_message SET user_id=?, trade_id=?, text=?, media=? WHERE id=?";
    private static final String DELETE_MESSAGE = "DELETE FROM trade_message WHERE id=?";

    private DataSource dataSource;


    private MessageDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static synchronized MessageDAO getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new MessageDAO(dataSource);
        }
        return instance;
    }

    @Override
    public Optional<MessageEntity> get(long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_MESSAGE)) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToMessageEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<MessageEntity> getAll() {
        List<MessageEntity> result = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_MESSAGES)
        ) {
            while (resultSet.next()) {
                result.add(mapResultSetToMessageEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void create(MessageEntity messageEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_MESSAGE)) {
            ps.setInt(1, messageEntity.getUser().getId());
            ps.setInt(2, messageEntity.getTrade().getId());
            ps.setString(3, messageEntity.getText());
            ps.setString(4, messageEntity.getMedia());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(MessageEntity messageEntity, String[] params) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_MESSAGE)) {
            ps.setInt(1, messageEntity.getUser().getId());
            ps.setInt(2, messageEntity.getTrade().getId());
            ps.setString(3, messageEntity.getText());
            ps.setString(4, messageEntity.getMedia());
            ps.setInt(5, messageEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(MessageEntity messageEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_MESSAGE)) {
            ps.setInt(1, messageEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private MessageEntity mapResultSetToMessageEntity(ResultSet resultSet) throws SQLException {
        return new MessageEntity(
                resultSet.getInt(ID_LABEL),
                UserDAO.getInstance(dataSource).get(resultSet.getInt(USER_ID_LABEL)).orElseThrow(),
                TradeDAO.getInstance(dataSource).get(resultSet.getInt(TRADE_ID_LABEL)).orElseThrow(),
                resultSet.getString(TEXT_LABEL),
                resultSet.getString(MEDIA_LABEL),
                resultSet.getTimestamp(CREATED_AT_LABEL)
        );
    }
}
