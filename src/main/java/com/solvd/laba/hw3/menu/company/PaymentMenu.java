package com.solvd.laba.hw3.menu.company;

import com.solvd.laba.hw3.enums.CurrencyType;
import com.solvd.laba.hw3.menu.input.InputReader;
import com.solvd.laba.hw3.model.payment.CardPayment;
import com.solvd.laba.hw3.model.payment.CashPayment;
import com.solvd.laba.hw3.model.route.TransportOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.Scanner;

public class PaymentMenu {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void addPayment(Scanner scanner, LocalDate orderDate, TransportOrder transportOrder) {
        LOGGER.info("Choose a currency option:");
        LOGGER.info("1. USD (US Dollar)");
        LOGGER.info("2. EUR (Euro)");
        LOGGER.info("3. GBP (British Pound)");

        LOGGER.info("Enter the currency option (1, 2, 3): ");
        int currencyOption = scanner.nextInt();
        scanner.nextLine(); // Consuming the newline character

        CurrencyType selectedCurrency;
        try {
            selectedCurrency = CurrencyType.getByOption(currencyOption);
        } catch (IllegalArgumentException e) {
            LOGGER.info("Invalid currency option. Defaulting to USD.");
            selectedCurrency = CurrencyType.USD;
        }

        LOGGER.info("Payment amount: ");
        double paymentAmount = InputReader.readDoubleData(scanner);

        LOGGER.info("Choose a payment method:");
        LOGGER.info("1. Cash");
        LOGGER.info("2. Credit");

        int paymentMethodOption = scanner.nextInt();
        scanner.nextLine(); // Consuming the newline character


        switch (paymentMethodOption) {
            case 1:
                transportOrder.setPayment(new CashPayment(orderDate, paymentAmount, selectedCurrency));
                break;

            case 2:
                LOGGER.info("Credit card number: ");
                String creditCardNumber = scanner.nextLine();
                transportOrder.setPayment(new CardPayment(orderDate, paymentAmount, selectedCurrency, creditCardNumber));
                break;

            default:
                LOGGER.info("Invalid payment method option. Defaulting to Cash.");
                transportOrder.setPayment(new CashPayment(orderDate, paymentAmount, selectedCurrency));
                break;
        }
    }


}
