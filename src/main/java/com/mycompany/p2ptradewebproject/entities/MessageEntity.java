package com.mycompany.p2ptradewebproject.entities;

import java.sql.Timestamp;

public class MessageEntity {
    private Integer id;
    private UserEntity user;
    private TradeEntity trade;
    private String text;
    private String media;
    private Timestamp createdAt;


    public MessageEntity(Integer id, UserEntity user, TradeEntity trade, String text, String media, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.trade = trade;
        this.text = text;
        this.media = media;
        this.createdAt = createdAt;
    }

    public MessageEntity(UserEntity user, TradeEntity trade, String text, String media) {
        this.user = user;
        this.trade = trade;
        this.text = text;
        this.media = media;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TradeEntity getTrade() {
        return trade;
    }

    public void setTrade(TradeEntity trade) {
        this.trade = trade;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
