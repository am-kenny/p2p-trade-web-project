package com.mycompany.p2ptradewebproject.persistence.entities;

import java.util.Objects;

public class BankAccountEntity {
    private Integer id;
    private Integer cardNumber;
    private BankEntity bank;
    private UserEntity user;
    private CurrencyEntity currency;
    private String cardholderName;

    public BankAccountEntity(Integer id, Integer cardNumber, BankEntity bank, UserEntity user, CurrencyEntity currency, String cardholderName) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.bank = bank;
        this.user = user;
        this.currency = currency;
        this.cardholderName = cardholderName;
    }

    public BankAccountEntity(Integer cardNumber, BankEntity bank, UserEntity user, CurrencyEntity currency, String cardholderName) {
        this.cardNumber = cardNumber;
        this.bank = bank;
        this.user = user;
        this.currency = currency;
        this.cardholderName = cardholderName;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BankEntity getBank() {
        return bank;
    }

    public void setBank(BankEntity bank) {
        this.bank = bank;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CurrencyEntity getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEntity currency) {
        this.currency = currency;
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
                ", bank=" + bank +
                ", user=" + user +
                ", currency=" + currency +
                ", cardholderName='" + cardholderName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountEntity that = (BankAccountEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(cardNumber, that.cardNumber) && Objects.equals(bank, that.bank) && Objects.equals(user, that.user) && Objects.equals(currency, that.currency) && Objects.equals(cardholderName, that.cardholderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, bank, user, currency, cardholderName);
    }
}
