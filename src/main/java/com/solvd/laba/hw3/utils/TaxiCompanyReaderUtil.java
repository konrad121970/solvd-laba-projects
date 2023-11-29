package com.solvd.laba.hw3.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.solvd.laba.hw3.model.TaxiCompany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.invoke.MethodHandles;

public class TaxiCompanyReaderUtil {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static TaxiCompany readFromFile(String filePath) throws Exception {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            return gson.fromJson(new FileReader(filePath), TaxiCompany.class);

        } catch (FileNotFoundException e) {
            LOGGER.error("Error in deserialization TaxiCompany data: " + e.getMessage());
            throw new RuntimeException();
        }
    }
}
