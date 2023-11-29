package com.solvd.laba.hw3.model.payment;

import com.solvd.laba.hw3.enums.CurrencyType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public final class CashPayment extends Payment {
    private static final Logger LOGGER = LogManager.getLogger(CashPayment.class);

    public CashPayment(LocalDate date, Double amount, CurrencyType currency) {
        super(date, amount, currency);
    }

    @Override
    public void processPayment() {
        LOGGER.info("The Cash payment has just been processed!");
    }
}
