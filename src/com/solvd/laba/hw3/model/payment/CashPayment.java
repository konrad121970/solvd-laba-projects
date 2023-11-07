package com.solvd.laba.hw3.model.payment;

import java.time.LocalDate;

public class CashPayment extends Payment {
    public CashPayment(LocalDate date, Double amount) {
        super(date, amount);
    }
}
