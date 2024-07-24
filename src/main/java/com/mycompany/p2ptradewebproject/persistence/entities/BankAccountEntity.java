package com.mycompany.p2ptradewebproject.persistence.entities;

import java.util.Objects;

public class BankAccountEntity {
    private Integer id;
    private Integer cardNumber;
    private Integer bankId;
    private Integer userId;
    private Integer currencyId;
    private String cardholderName;


    public BankAccountEntity() {}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    @Override
    public String toString() {
        return "BankAccountEntity{" +
                "id=" + id +
                ", cardNumber=" + cardNumber +
                ", bankId=" + bankId +
                ", userId=" + userId +
                ", currencyId=" + currencyId +
                ", cardholderName='" + cardholderName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountEntity that = (BankAccountEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(cardNumber, that.cardNumber) && Objects.equals(bankId, that.bankId) && Objects.equals(userId, that.userId) && Objects.equals(currencyId, that.currencyId) && Objects.equals(cardholderName, that.cardholderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, bankId, userId, currencyId, cardholderName);
    }
}
