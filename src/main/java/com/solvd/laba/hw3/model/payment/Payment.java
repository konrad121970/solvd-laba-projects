package com.solvd.laba.hw3.model.payment;

import com.solvd.laba.hw3.common.enums.CurrencyType;
import com.solvd.laba.hw3.common.interfaces.Displayable;
import com.solvd.laba.hw3.common.interfaces.Payable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Payment implements Payable, Displayable, Serializable {
    private static final Logger LOGGER = LogManager.getLogger(Payment.class);
    private LocalDate date;
    private Double amount;
    private boolean isPaid;
    private CurrencyType currencyType;

    public Payment(LocalDate date, Double amount, CurrencyType currencyType) {
        this.date = date;
        this.amount = amount;
        this.currencyType = currencyType;
    }

    public CurrencyType getCurrency() {
        return currencyType;
    }

    public void setCurrency(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "date=" + date +
                ", amount=" + amount +
                ", isPaid=" + isPaid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return isPaid == payment.isPaid && Objects.equals(date, payment.date) && Objects.equals(amount, payment.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, isPaid);
    }

    @Override
    public Double showPaymentAmount() {
        return this.amount;
    }

    @Override
    public boolean isPaymentDone() {
        return this.isPaid;
    }

    @Override
    public void display() {
        LOGGER.info("Payment Information:");
        LOGGER.info("Date: " + date);
        LOGGER.info("Amount: " + amount + " " + currencyType);
    }

    @Override
    public void showDetails() {
        LOGGER.info("Payment Information:");
        LOGGER.info("Date: " + date);
        LOGGER.info("Amount: " + amount + " " + currencyType);
        LOGGER.info("Payment Status: " + (isPaid ? "Paid" : "Pending"));
    }
}
