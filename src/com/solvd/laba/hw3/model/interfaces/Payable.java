package com.solvd.laba.hw3.model.interfaces;

public interface Payable {
    void processPayment();

    Double showPaymentAmount();

    boolean isPaymentDone();

}
