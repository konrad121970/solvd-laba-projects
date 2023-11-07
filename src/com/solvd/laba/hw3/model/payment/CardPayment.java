package com.solvd.laba.hw3.model.payment;

import java.time.LocalDate;

public class CardPayment extends Payment {
    public CardPayment(LocalDate date, Double amount) {
        super(date, amount);
    }
}
