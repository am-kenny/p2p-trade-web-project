package com.mycompany.p2ptradewebproject.persistence.jdbc.mapper;

import com.mycompany.p2ptradewebproject.persistence.entities.TradeEntity;
import com.mycompany.p2ptradewebproject.persistence.entities.TradeStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TradeMapper implements ResultSetMapper<TradeEntity> {
    private static final String TABLE_NAME = "trade";
    private static final String MYSQL_QUERY_SELECT_ALL = "SELECT id, initiator_user_id, responder_user_id, is_seller, trade_currency_id, trade_currency_amount, exchange_currency_id, exchange_rate, status, is_confirmed_by_initiator, is_confirmed_by_responder, created_at, replied_at, closed_at FROM " + TABLE_NAME;
    private static final String MYSQL_QUERY_COUNT = "SELECT COUNT(id) FROM " + TABLE_NAME;
    private static final String ID_LABEL = "id";
    private static final String INITIATOR_USER_ID_LABEL = "initiator_user_id";
    private static final String RESPONDER_USER_ID_LABEL = "responder_user_id";
    private static final String IS_SELLER_LABEL = "is_seller";
    private static final String TRADE_CURRENCY_ID_LABEL = "trade_currency_id";
    private static final String TRADE_CURRENCY_AMOUNT_LABEL = "trade_currency_amount";
    private static final String EXCHANGE_CURRENCY_ID_LABEL = "exchange_currency_id";
    private static final String EXCHANGE_RATE_LABEL = "exchange_rate";
    private static final String STATUS_LABEL = "status";
    private static final String IS_CONFIRMED_BY_INITIATOR = "is_confirmed_by_initiator";
    private static final String IS_CONFIRMED_BY_RESPONDER = "is_confirmed_by_responder";
    private static final String CREATED_AT_LABEL = "created_at";
    private static final String REPLIED_AT_LABEL = "replied_at";
    private static final String CLOSED_AT_LABEL = "closed_at";

    private static TradeMapper instance;

    private TradeMapper() {}

    public static synchronized TradeMapper getInstance() {
        if (instance == null) {
            instance = new TradeMapper();
        }
        return instance;
    }

    @Override
    public TradeEntity map(ResultSet resultSet) throws SQLException {
        TradeEntity trade = new TradeEntity();
        trade.setId(resultSet.getInt(ID_LABEL));
        trade.setInitiatorUserId(resultSet.getInt(INITIATOR_USER_ID_LABEL));
        trade.setResponderUserId(resultSet.getInt(RESPONDER_USER_ID_LABEL));
        trade.setIsSeller(resultSet.getBoolean(IS_SELLER_LABEL));
        trade.setTradeCurrencyId(resultSet.getInt(TRADE_CURRENCY_ID_LABEL));
        trade.setTradeCurrencyAmount(resultSet.getFloat(TRADE_CURRENCY_AMOUNT_LABEL));
        trade.setExchangeCurrencyId(resultSet.getInt(EXCHANGE_CURRENCY_ID_LABEL));
        trade.setExchangeRate(resultSet.getFloat(EXCHANGE_RATE_LABEL));
        trade.setStatus(TradeStatus.valueOf(resultSet.getString(STATUS_LABEL)));
        trade.setIsConfirmedByInitiator(resultSet.getBoolean(IS_CONFIRMED_BY_INITIATOR));
        trade.setIsConfirmedByResponder(resultSet.getBoolean(IS_CONFIRMED_BY_RESPONDER));
        trade.setCreatedAt(resultSet.getTimestamp(CREATED_AT_LABEL));
        trade.setRepliedAt(resultSet.getTimestamp(REPLIED_AT_LABEL));
        trade.setClosedAt(resultSet.getTimestamp(CLOSED_AT_LABEL));

        return trade;
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
