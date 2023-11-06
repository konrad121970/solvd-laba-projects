package com.solvd.laba.hw3.model.interfaces;

import java.time.LocalDate;

public interface Maintainable {
    void scheduleMaintenance(LocalDate date);
    void doMaintenance();
}
