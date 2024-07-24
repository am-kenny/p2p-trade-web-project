package com.mycompany.p2ptradewebproject.persistence.jdbc;

import com.mycompany.p2ptradewebproject.persistence.connection.AbstractDataSource;
import com.mycompany.p2ptradewebproject.persistence.jdbc.interfaces.IDAOFeedback;
import com.mycompany.p2ptradewebproject.persistence.entities.FeedbackEntity;
import com.mycompany.p2ptradewebproject.persistence.jdbc.mapper.ResultSetMapper;

import java.sql.*;
import java.util.logging.Logger;

public class FeedbackDAO extends GenericDAO<FeedbackEntity> implements IDAOFeedback {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getSimpleName());
    private static FeedbackDAO instance = null;
    private AbstractDataSource dataSource;
    private ResultSetMapper<FeedbackEntity> mapper;

    private static final String INSERT_FEEDBACK = "INSERT INTO trade_feedback(author_user_id, trade_id, is_positive, text) VALUES(?,?,?,?)";
    private static final String UPDATE_FEEDBACK = "UPDATE trade_feedback SET author_user_id=?, trade_id=?, is_positive=?, text=? WHERE id=?";
    private static final String DELETE_FEEDBACK = "DELETE FROM trade_feedback WHERE id=?";


    private FeedbackDAO(AbstractDataSource dataSource, ResultSetMapper<FeedbackEntity> mapper) {
        super(dataSource, mapper);
        this.dataSource = dataSource;
        this.mapper = mapper;
    }

    public static synchronized FeedbackDAO getInstance(AbstractDataSource dataSource, ResultSetMapper<FeedbackEntity> mapper) {
        if (instance == null) {
            instance = new FeedbackDAO(dataSource, mapper);
        }
        return instance;
    }


    @Override
    public void create(FeedbackEntity feedbackEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_FEEDBACK)) {
            ps.setInt(1, feedbackEntity.getAuthorId());
            ps.setInt(2, feedbackEntity.getTradeId());
            ps.setBoolean(3, feedbackEntity.getIsPositive());
            ps.setString(4, feedbackEntity.getText());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void update(FeedbackEntity feedbackEntity, String[] params) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_FEEDBACK)) {
            ps.setInt(1, feedbackEntity.getAuthorId());
            ps.setInt(2, feedbackEntity.getTradeId());
            ps.setBoolean(3, feedbackEntity.getIsPositive());
            ps.setString(4, feedbackEntity.getText());
            ps.setInt(5, feedbackEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void delete(FeedbackEntity feedbackEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_FEEDBACK)) {
            ps.setInt(1, feedbackEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }

}
