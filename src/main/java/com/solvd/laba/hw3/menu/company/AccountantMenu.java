package com.solvd.laba.hw3.menu.company;

import com.solvd.laba.hw3.model.TaxiCompany;
import com.solvd.laba.hw3.model.people.employees.Accountant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

public class AccountantMenu {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void generateFinancialReportByMonth(Scanner scanner, TaxiCompany taxiCompany) {
        LOGGER.info("Enter the month for which you want to generate the financial report (1-12): ");
        int month = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        try {
            // Validate the month input
            if (month < 1 || month > 12) {
                LOGGER.warn("Invalid month. Please enter a number between 1 and 12.");
                return;
            }

            LocalDate selectedDate = LocalDate.now().withMonth(month);

            generateReport(scanner, taxiCompany, selectedDate);

        } catch (Exception ex) {
            LOGGER.error("Error generating the financial report: " + ex.getMessage(), ex);
        }
    }

    private static void generateReport(Scanner scanner, TaxiCompany taxiCompany, LocalDate selectedDate) {
        Set<Accountant> accountants = taxiCompany.getAccountants().get();

        List<Accountant> accountantList = new ArrayList<>(accountants);

        Accountant selectedAccountant = chooseAccountant(scanner, accountantList);
        if (selectedAccountant != null) {
            selectedAccountant.generateFinancialReportByMonth(taxiCompany.getTransportOrders().orElse(Collections.emptyList()), selectedDate);
        }
    }

    private static Accountant chooseAccountant(Scanner scanner, List<Accountant> accountants) {
        LOGGER.info("Select an accountant:");

        IntStream.range(0, accountants.size())
                .forEach(i -> LOGGER.info((i + 1) + ". "
                        + accountants.get(i).getFirstName()
                        + " " + accountants.get(i).getLastName()));


        LOGGER.info("Enter the number corresponding to the accountant: ");
        int accountantIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline character

        try {
            return accountants.get(accountantIndex);
        } catch (IndexOutOfBoundsException ex) {
            LOGGER.warn("Invalid accountant selection. Please enter a valid number.");
            return null;
        }
    }

}
