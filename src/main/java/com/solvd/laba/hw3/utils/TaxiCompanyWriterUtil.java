package com.solvd.laba.hw3.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.invoke.MethodHandles;

public class TaxiCompanyWriterUtil {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void writeToFile(String filePath, Object taxiCompany) {
        Writer writer;
        try {
            writer = new FileWriter(filePath);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(taxiCompany, writer);
            LOGGER.info("Taxi Company data succesfully saved!");
            writer.close();
        } catch (IOException e) {
            LOGGER.error("Error in writing TaxiCompany data: " + e.getMessage());
        }
    }
}
