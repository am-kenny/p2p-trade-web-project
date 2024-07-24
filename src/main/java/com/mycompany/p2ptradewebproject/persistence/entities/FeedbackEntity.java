package com.mycompany.p2ptradewebproject.persistence.entities;

import java.util.Objects;

public class FeedbackEntity {
    private Integer id;
    private Integer authorId;
    private Integer tradeId;
    private Boolean isPositive;
    private String text;


    public FeedbackEntity() {}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
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
                ", authorId=" + authorId +
                ", trade=" + tradeId +
                ", isPositive=" + isPositive +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackEntity that = (FeedbackEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(authorId, that.authorId) && Objects.equals(tradeId, that.tradeId) && Objects.equals(isPositive, that.isPositive) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, tradeId, isPositive, text);
    }
}
