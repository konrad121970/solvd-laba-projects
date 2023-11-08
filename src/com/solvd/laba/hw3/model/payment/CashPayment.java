package com.solvd.laba.hw3.model.payment;

import java.time.LocalDate;

public final class CashPayment extends Payment {

    public CashPayment(LocalDate date, Double amount) {
        super(date, amount);
    }

    @Override
    public void processPayment() {

    }
}
