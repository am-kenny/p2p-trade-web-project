package com.mycompany.p2ptradewebproject.persistence.dao;

import com.mycompany.p2ptradewebproject.persistence.connection.DataSource;
import com.mycompany.p2ptradewebproject.persistence.connection.EDatabaseType;
import com.mycompany.p2ptradewebproject.persistence.dao.interfaces.IDAOFeedback;
import com.mycompany.p2ptradewebproject.persistence.entities.FeedbackEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class FeedbackDAO implements IDAOFeedback {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getSimpleName());
    private static FeedbackDAO instance = null;

    private static final String ID_LABEL = "id";
    private static final String AUTHOR_USER_ID_LABEL = "author_user_id";
    private static final String TRADE_ID_LABEL = "trade_id";
    private static final String IS_POSITIVE_LABEL = "is_positive";
    private static final String TEXT_LABEL = "text";

    private static final String INSERT_FEEDBACK = "INSERT INTO trade_feedback(author_user_id, trade_id, is_positive, text) VALUES(?,?,?,?)";
    private static final String SELECT_ALL_FEEDBACKS = "SELECT * FROM trade_feedback";
    private static final String SELECT_FEEDBACK = "SELECT * FROM trade_feedback WHERE id=?";
    private static final String UPDATE_FEEDBACK = "UPDATE trade_feedback SET author_user_id=?, trade_id=?, is_positive=?, text=? WHERE id=?";
    private static final String DELETE_FEEDBACK = "DELETE FROM trade_feedback WHERE id=?";

    private DataSource dataSource;


    private FeedbackDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static synchronized FeedbackDAO getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new FeedbackDAO(dataSource);
        }
        return instance;
    }

    @Override
    public Optional<FeedbackEntity> get(long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_FEEDBACK)) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToFeedbackEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<FeedbackEntity> getAll() {
        List<FeedbackEntity> result = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_FEEDBACKS)
        ) {
            while (resultSet.next()) {
                result.add(mapResultSetToFeedbackEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void create(FeedbackEntity feedbackEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_FEEDBACK)) {
            ps.setInt(1, feedbackEntity.getAuthor().getId());
            ps.setInt(2, feedbackEntity.getTrade().getId());
            ps.setBoolean(3, feedbackEntity.getIsPositive());
            ps.setString(4, feedbackEntity.getText());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(FeedbackEntity feedbackEntity, String[] params) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_FEEDBACK)) {
            ps.setInt(1, feedbackEntity.getAuthor().getId());
            ps.setInt(2, feedbackEntity.getTrade().getId());
            ps.setBoolean(3, feedbackEntity.getIsPositive());
            ps.setString(4, feedbackEntity.getText());
            ps.setInt(5, feedbackEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(FeedbackEntity feedbackEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_FEEDBACK)) {
            ps.setInt(1, feedbackEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private FeedbackEntity mapResultSetToFeedbackEntity(ResultSet resultSet) throws SQLException {
        return new FeedbackEntity(
                resultSet.getInt(ID_LABEL),
                UserDAO.getInstance(DataSource.getInstance(EDatabaseType.MYSQL)).get(resultSet.getInt(AUTHOR_USER_ID_LABEL)).orElseThrow(),
                TradeDAO.getInstance(dataSource).get(resultSet.getInt(TRADE_ID_LABEL)).orElseThrow(),
                resultSet.getBoolean(IS_POSITIVE_LABEL),
                resultSet.getString(TEXT_LABEL)
        );
    }
}
