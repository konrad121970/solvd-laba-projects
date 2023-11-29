package com.solvd.laba.hw3.model.payment;

import com.solvd.laba.hw3.enums.CurrencyType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Objects;

public final class CardPayment extends Payment {
    private static final Logger LOGGER = LogManager.getLogger(CardPayment.class);
    private String cardNumber;

    public CardPayment(LocalDate date, Double amount, CurrencyType currency) {
        super(date, amount, currency);
    }

    public CardPayment(LocalDate date, Double amount, CurrencyType currency, String cardNumber) {
        super(date, amount, currency);
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

/*    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }*/

    @Override
    public String toString() {
        return "CardPayment{" +
                "cardNumber='" + cardNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CardPayment that = (CardPayment) o;
        return Objects.equals(cardNumber, that.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cardNumber);
    }

    @Override
    public void processPayment() {
        LOGGER.info("Card Payment has been processed! CardNumber: " + this.cardNumber);
    }
}
