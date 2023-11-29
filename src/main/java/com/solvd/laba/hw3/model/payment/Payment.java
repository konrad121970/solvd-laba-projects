package com.solvd.laba.hw3.model.payment;

import com.solvd.laba.hw3.common.enums.CurrencyType;
import com.solvd.laba.hw3.common.interfaces.Displayable;
import com.solvd.laba.hw3.common.interfaces.Payable;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Payment implements Payable, Displayable {
    private LocalDate date;
    private Double amount;
    private boolean isPaid;
    private CurrencyType currency;

    public Payment(LocalDate date, Double amount, CurrencyType currency) {
        this.date = date;
        this.amount = amount;
        this.currency = currency;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
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

    //TODO:
    @Override
    public void display() {

    }

    @Override
    public void showDetails() {

    }
}
