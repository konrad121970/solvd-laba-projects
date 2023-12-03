package com.solvd.laba.hw3.utils;

import com.solvd.laba.hw3.model.TaxiCompany;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomersFileWriterUtil {
    private static final Logger LOGGER = LogManager.getLogger(CustomersFileWriterUtil.class);

    public static void writeCustomersToFile(TaxiCompany taxiCompany) {

        if (taxiCompany.getCustomers().isPresent()) {
            try {
                StringBuilder data = new StringBuilder();

                data.append("Consumer Information\n");


                taxiCompany.getCustomers().get().forEach(customer -> {
                    data.append("Name: ").append(customer.getFirstName()).append(" ").append(customer.getLastName()).append("\n");
                    data.append("Phone Number: ").append(customer.getPhoneNumber()).append("\n");
                    data.append("Spent Money: ").append(customer.getSpentMoney()).append("\n");
                    data.append("---------------\n");
                });

                FileUtils.writeStringToFile(new File("src/main/resources/customers_list"), data.toString(), StandardCharsets.UTF_8, true);
            } catch (IOException e) {
                LOGGER.error("Error saving customer  information to file: " + e.getMessage());
            }
        }
    }
}
