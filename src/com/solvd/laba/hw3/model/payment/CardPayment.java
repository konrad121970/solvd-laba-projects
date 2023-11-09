package com.solvd.laba.hw3.model.payment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Objects;

public final class CardPayment extends Payment {
    private static final Logger LOGGER = LogManager.getLogger(CardPayment.class);
    private String cardNumber;
    private String cardType;

    public CardPayment(LocalDate date, Double amount) {
        super(date, amount);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "CardPayment{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardType='" + cardType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CardPayment that = (CardPayment) o;
        return Objects.equals(cardNumber, that.cardNumber) && Objects.equals(cardType, that.cardType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cardNumber, cardType);
    }

    @Override
    public void processPayment() {
        LOGGER.info("Card Payment has been processed! CardNumber: " + this.cardNumber);
    }
}
