package com.mycompany.p2ptradewebproject.dao;

import com.mycompany.p2ptradewebproject.connection.DataSource;
import com.mycompany.p2ptradewebproject.dao.interfaces.IDAOTrade;
import com.mycompany.p2ptradewebproject.entities.TradeEntity;
import com.mycompany.p2ptradewebproject.entities.TradeStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class TradeDAO implements IDAOTrade {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getSimpleName());
    private static TradeDAO instance = null;

    private static final String ID_LABEL = "id";
    private static final String INITIATOR_USER_ID_LABEL = "initiator_user_id";
    private static final String RESPONDER_USER_ID_LABEL = "responder_user_id";
    private static final String IS_SELLER_LABEL = "is_seller";
    private static final String TRADE_CURRENCY_ID_LABEL = "trade_currency_id";
    private static final String TRADE_CURRENCY_AMOUNT_LABEL = "trade_currency_amount";
    private static final String EXCHANGE_CURRENCY_ID_LABEL = "exchange_currency_id";
    private static final String EXCHANGE_RATE_LABEL = "exchange_rate";
    private static final String STATUS_LABEL = "status";
    private static final String IS_CONFIRMED_BY_INITIATOR_LABEL = "is_confirmed_by_initiator";
    private static final String IS_CONFIRMED_BY_RESPONDER_LABEL = "is_confirmed_by_responder";
    private static final String CREATED_AT_LABEL = "created_at";
    private static final String REPLIED_AT_LABEL = "replied_at";
    private static final String CLOSED_AT_LABEL = "closed_at";

    private static final String INSERT_TRADE = "INSERT INTO trade(initiator_user_id, is_seller, trade_currency_id, trade_currency_amount, exchange_currency_id, exchange_rate) VALUES(?,?,?,?,?,?)";
    private static final String SELECT_ALL_TRADES = "SELECT * FROM trade";
    private static final String SELECT_TRADE = "SELECT * FROM trade WHERE id=?";
    private static final String UPDATE_TRADE = "UPDATE trade SET responder_user_id=?, is_seller=?, trade_currency_id=?, trade_currency_amount=?, exchange_currency_id=?, exchange_rate=?, status=?, is_confirmed_by_initiator=?, is_confirmed_by_responder=? WHERE id=?";
    private static final String DELETE_TRADE = "DELETE FROM trade WHERE id=?";

    private DataSource dataSource;


    private TradeDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static synchronized TradeDAO getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new TradeDAO(dataSource);
        }
        return instance;
    }

    @Override
    public Optional<TradeEntity> get(long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_TRADE)) {
            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToTradeEntity(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<TradeEntity> getAll() {
        List<TradeEntity> result = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_TRADES)
        ) {
            while (resultSet.next()) {
                result.add(mapResultSetToTradeEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void create(TradeEntity tradeEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_TRADE)) {
            ps.setInt(1, tradeEntity.getInitiatorUserId());
            ps.setBoolean(2, tradeEntity.getIsSeller());
            ps.setInt(3, tradeEntity.getTradeCurrencyId());
            ps.setFloat(4, tradeEntity.getTradeCurrencyAmount());
            ps.setInt(5, tradeEntity.getExchangeCurrencyId());
            ps.setFloat(6, tradeEntity.getExchangeRate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(TradeEntity tradeEntity, String[] params) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_TRADE)) {
            ps.setInt(1, tradeEntity.getResponderUserId());
            ps.setBoolean(2, tradeEntity.getIsSeller());
            ps.setInt(3, tradeEntity.getTradeCurrencyId());
            ps.setFloat(4, tradeEntity.getTradeCurrencyAmount());
            ps.setInt(5, tradeEntity.getExchangeCurrencyId());
            ps.setFloat(6, tradeEntity.getExchangeRate());
            ps.setString(7, tradeEntity.getStatus().name());
            ps.setBoolean(8, tradeEntity.getIsConfirmedByInitiator());
            ps.setBoolean(9, tradeEntity.getIsConfirmedByResponder());
            ps.setInt(10, tradeEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(TradeEntity tradeEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_TRADE)) {
            ps.setInt(1, tradeEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TradeEntity mapResultSetToTradeEntity(ResultSet resultSet) throws SQLException {
        return new TradeEntity(
                resultSet.getInt(ID_LABEL),
                resultSet.getInt(INITIATOR_USER_ID_LABEL),
                resultSet.getInt(RESPONDER_USER_ID_LABEL),
                resultSet.getBoolean(IS_SELLER_LABEL),
                resultSet.getInt(TRADE_CURRENCY_ID_LABEL),
                resultSet.getFloat(TRADE_CURRENCY_AMOUNT_LABEL),
                resultSet.getInt(EXCHANGE_CURRENCY_ID_LABEL),
                resultSet.getFloat(EXCHANGE_RATE_LABEL),
                TradeStatus.valueOf(resultSet.getString(STATUS_LABEL)),
                resultSet.getBoolean(IS_CONFIRMED_BY_INITIATOR_LABEL),
                resultSet.getBoolean(IS_CONFIRMED_BY_RESPONDER_LABEL),
                resultSet.getTimestamp(CREATED_AT_LABEL),
                resultSet.getTimestamp(REPLIED_AT_LABEL),
                resultSet.getTimestamp(CLOSED_AT_LABEL)
        );
    }
}
