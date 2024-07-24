package com.mycompany.p2ptradewebproject.persistence.jdbc;

import com.mycompany.p2ptradewebproject.persistence.connection.AbstractDataSource;
import com.mycompany.p2ptradewebproject.persistence.jdbc.interfaces.IDAOMessage;
import com.mycompany.p2ptradewebproject.persistence.entities.MessageEntity;
import com.mycompany.p2ptradewebproject.persistence.jdbc.mapper.ResultSetMapper;

import java.sql.*;
import java.util.logging.Logger;

public class MessageDAO extends GenericDAO<MessageEntity> implements IDAOMessage {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getSimpleName());
    private static MessageDAO instance = null;
    private AbstractDataSource dataSource;
    private ResultSetMapper<MessageEntity> mapper;

    private static final String INSERT_MESSAGE = "INSERT INTO trade_message(user_id, trade_id, text, media) VALUES(?,?,?,?)";
    private static final String UPDATE_MESSAGE = "UPDATE trade_message SET user_id=?, trade_id=?, text=?, media=? WHERE id=?";
    private static final String DELETE_MESSAGE = "DELETE FROM trade_message WHERE id=?";


    private MessageDAO(AbstractDataSource dataSource, ResultSetMapper<MessageEntity> mapper) {
        super(dataSource, mapper);
        this.dataSource = dataSource;
        this.mapper = mapper;
    }

    public static synchronized MessageDAO getInstance(AbstractDataSource dataSource, ResultSetMapper<MessageEntity> mapper) {
        if (instance == null) {
            instance = new MessageDAO(dataSource, mapper);
        }
        return instance;
    }


    @Override
    public void create(MessageEntity messageEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_MESSAGE)) {
            ps.setInt(1, messageEntity.getUserId());
            ps.setInt(2, messageEntity.getTradeId());
            ps.setString(3, messageEntity.getText());
            ps.setString(4, messageEntity.getMedia());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void update(MessageEntity messageEntity, String[] params) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_MESSAGE)) {
            ps.setInt(1, messageEntity.getUserId());
            ps.setInt(2, messageEntity.getTradeId());
            ps.setString(3, messageEntity.getText());
            ps.setString(4, messageEntity.getMedia());
            ps.setInt(5, messageEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void delete(MessageEntity messageEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_MESSAGE)) {
            ps.setInt(1, messageEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

}
