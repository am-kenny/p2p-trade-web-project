package com.mycompany.p2ptradewebproject.entities;

import java.sql.Timestamp;
import java.util.Objects;

public class TradeEntity {
    private Integer id;
    private Integer initiatorUserId;
    private Integer responderUserId;
    private Boolean isSeller;
    private Integer tradeCurrencyId;
    private Float tradeCurrencyAmount;
    private Integer exchangeCurrencyId;
    private Float exchangeRate;
    private TradeStatus status;
    private Boolean isConfirmedByInitiator;
    private Boolean isConfirmedByResponder;
    private Timestamp createdAt;
    private Timestamp repliedAt;
    private Timestamp closedAt;

    public TradeEntity(Integer id, Integer initiatorUserId, Integer responderUserId, Boolean isSeller, Integer tradeCurrencyId, Float tradeCurrencyAmount, Integer exchangeCurrencyId, Float exchangeRate, TradeStatus status, Boolean isConfirmedByInitiator, Boolean isConfirmedByResponder, Timestamp createdAt, Timestamp repliedAt, Timestamp closedAt) {
        this.id = id;
        this.initiatorUserId = initiatorUserId;
        this.responderUserId = responderUserId;
        this.isSeller = isSeller;
        this.tradeCurrencyId = tradeCurrencyId;
        this.tradeCurrencyAmount = tradeCurrencyAmount;
        this.exchangeCurrencyId = exchangeCurrencyId;
        this.exchangeRate = exchangeRate;
        this.status = status;
        this.isConfirmedByInitiator = isConfirmedByInitiator;
        this.isConfirmedByResponder = isConfirmedByResponder;
        this.createdAt = createdAt;
        this.repliedAt = repliedAt;
        this.closedAt = closedAt;
    }

    public TradeEntity(Integer initiatorUserId, Boolean isSeller, Integer tradeCurrencyId, Float tradeCurrencyAmount, Integer exchangeCurrencyId, Float exchangeRate) {
        this.initiatorUserId = initiatorUserId;
        this.isSeller = isSeller;
        this.tradeCurrencyId = tradeCurrencyId;
        this.tradeCurrencyAmount = tradeCurrencyAmount;
        this.exchangeCurrencyId = exchangeCurrencyId;
        this.exchangeRate = exchangeRate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInitiatorUserId() {
        return initiatorUserId;
    }

    public void setInitiatorUserId(Integer initiatorUserId) {
        this.initiatorUserId = initiatorUserId;
    }

    public Integer getResponderUserId() {
        return responderUserId;
    }

    public void setResponderUserId(Integer responderUserId) {
        this.responderUserId = responderUserId;
    }

    public Boolean getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(Boolean isSeller) {
        this.isSeller = isSeller;
    }

    public Integer getTradeCurrencyId() {
        return tradeCurrencyId;
    }

    public void setTradeCurrencyId(Integer tradeCurrencyId) {
        this.tradeCurrencyId = tradeCurrencyId;
    }

    public Float getTradeCurrencyAmount() {
        return tradeCurrencyAmount;
    }

    public void setTradeCurrencyAmount(Float tradeCurrencyAmount) {
        this.tradeCurrencyAmount = tradeCurrencyAmount;
    }

    public Integer getExchangeCurrencyId() {
        return exchangeCurrencyId;
    }

    public void setExchangeCurrencyId(Integer exchangeCurrencyId) {
        this.exchangeCurrencyId = exchangeCurrencyId;
    }

    public Float getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Float exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public TradeStatus getStatus() {
        return status;
    }

    public void setStatus(TradeStatus status) {
        this.status = status;
    }

    public Boolean getIsConfirmedByInitiator() {
        return isConfirmedByInitiator;
    }

    public void setIsConfirmedByInitiator(Boolean isConfirmedByInitiator) {
        this.isConfirmedByInitiator = isConfirmedByInitiator;
    }

    public Boolean getIsConfirmedByResponder() {
        return isConfirmedByResponder;
    }

    public void setIsConfirmedByResponder(Boolean isConfirmedByResponder) {
        this.isConfirmedByResponder = isConfirmedByResponder;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getRepliedAt() {
        return repliedAt;
    }

    public void setRepliedAt(Timestamp repliedAt) {
        this.repliedAt = repliedAt;
    }

    public Timestamp getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Timestamp closedAt) {
        this.closedAt = closedAt;
    }

    @Override
    public String toString() {
        return "TradeEntity{" +
                "id=" + id +
                ", initiatorUserId=" + initiatorUserId +
                ", responderUserId=" + responderUserId +
                ", isSeller=" + isSeller +
                ", tradeCurrencyId=" + tradeCurrencyId +
                ", tradeCurrencyAmount=" + tradeCurrencyAmount +
                ", exchangeCurrencyId=" + exchangeCurrencyId +
                ", exchangeRate=" + exchangeRate +
                ", status=" + status +
                ", isConfirmedByInitiator=" + isConfirmedByInitiator +
                ", isConfirmedByResponder=" + isConfirmedByResponder +
                ", createdAt=" + createdAt +
                ", repliedAt=" + repliedAt +
                ", closedAt=" + closedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeEntity that = (TradeEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(initiatorUserId, that.initiatorUserId) && Objects.equals(responderUserId, that.responderUserId) && Objects.equals(isSeller, that.isSeller) && Objects.equals(tradeCurrencyId, that.tradeCurrencyId) && Objects.equals(tradeCurrencyAmount, that.tradeCurrencyAmount) && Objects.equals(exchangeCurrencyId, that.exchangeCurrencyId) && Objects.equals(exchangeRate, that.exchangeRate) && Objects.equals(status, that.status) && Objects.equals(isConfirmedByInitiator, that.isConfirmedByInitiator) && Objects.equals(isConfirmedByResponder, that.isConfirmedByResponder) && Objects.equals(createdAt, that.createdAt) && Objects.equals(repliedAt, that.repliedAt) && Objects.equals(closedAt, that.closedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, initiatorUserId, responderUserId, isSeller, tradeCurrencyId, tradeCurrencyAmount, exchangeCurrencyId, exchangeRate, status, isConfirmedByInitiator, isConfirmedByResponder, createdAt, repliedAt, closedAt);
    }
}

