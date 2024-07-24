package com.mycompany.p2ptradewebproject.persistence.jdbc;

import com.mycompany.p2ptradewebproject.persistence.connection.AbstractDataSource;
import com.mycompany.p2ptradewebproject.persistence.jdbc.interfaces.IDAOTrade;
import com.mycompany.p2ptradewebproject.persistence.entities.TradeEntity;
import com.mycompany.p2ptradewebproject.persistence.jdbc.mapper.ResultSetMapper;

import java.sql.*;
import java.util.logging.Logger;

public class TradeDAO extends GenericDAO<TradeEntity> implements IDAOTrade {

    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getSimpleName());
    private static TradeDAO instance = null;
    private final AbstractDataSource dataSource;

    private static final String INSERT_TRADE = "INSERT INTO trade(initiator_user_id, is_seller, trade_currency_id, trade_currency_amount, exchange_currency_id, exchange_rate) VALUES(?,?,?,?,?,?)";
    private static final String UPDATE_TRADE = "UPDATE trade SET responder_user_id=?, is_seller=?, trade_currency_id=?, trade_currency_amount=?, exchange_currency_id=?, exchange_rate=?, status=?, is_confirmed_by_initiator=?, is_confirmed_by_responder=? WHERE id=?";
    private static final String DELETE_TRADE = "DELETE FROM trade WHERE id=?";


    private TradeDAO(AbstractDataSource dataSource, ResultSetMapper<TradeEntity> mapper) {
        super(dataSource, mapper);
        this.dataSource = dataSource;
    }

    public static synchronized TradeDAO getInstance(AbstractDataSource dataSource, ResultSetMapper<TradeEntity> mapper) {
        if (instance == null) {
            instance = new TradeDAO(dataSource, mapper);
        }
        return instance;
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
            LOGGER.severe(e.getMessage());
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
            LOGGER.severe(e.getMessage());
        }
    }

    @Override
    public void delete(TradeEntity tradeEntity) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_TRADE)) {
            ps.setInt(1, tradeEntity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
