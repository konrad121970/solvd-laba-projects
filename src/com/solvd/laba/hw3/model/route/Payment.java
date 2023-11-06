package com.solvd.laba.hw3.model.route;

import com.solvd.laba.hw3.model.interfaces.Payable;

import java.time.LocalDate;

public class Payment implements Payable {
    private LocalDate date;
    private Double amount;
    private boolean isPaid;

    public Payment(LocalDate date, Double amount) {
        this.date = date;
        this.amount = amount;
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
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Double showPaymentAmount() {
        return this.amount;
    }

    @Override
    public boolean isPaymentDone() {
        return this.isPaid;
    }
}
