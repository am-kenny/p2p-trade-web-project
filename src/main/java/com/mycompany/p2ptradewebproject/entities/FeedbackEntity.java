package com.mycompany.p2ptradewebproject.entities;

import java.util.Objects;

public class FeedbackEntity {
    private Integer id;
    private UserEntity author;
    private TradeEntity trade;
    private Boolean isPositive;
    private String text;


    public FeedbackEntity(Integer id, UserEntity author, TradeEntity trade, Boolean isPositive, String text) {
        this.id = id;
        this.author = author;
        this.trade = trade;
        this.isPositive = isPositive;
        this.text = text;
    }


    public FeedbackEntity(UserEntity author, TradeEntity trade, Boolean isPositive, String text) {
        this.author = author;
        this.trade = trade;
        this.isPositive = isPositive;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }


    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public TradeEntity getTrade() {
        return trade;
    }

    public void setTrade(TradeEntity trade) {
        this.trade = trade;
    }

    public Boolean getIsPositive() {
        return isPositive;
    }

    public void setIsPositive(Boolean isPositive) {
        this.isPositive = isPositive;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "FeedbackEntity{" +
                "id=" + id +
                ", author=" + author +
                ", trade=" + trade +
                ", isPositive=" + isPositive +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackEntity that = (FeedbackEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(author, that.author) && Objects.equals(trade, that.trade) && Objects.equals(isPositive, that.isPositive) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, trade, isPositive, text);
    }
}
