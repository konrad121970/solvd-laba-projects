package com.solvd.laba.hw3.model.exceptions;

public class InvalidNextMaintenanceDateException extends RuntimeException {
    public InvalidNextMaintenanceDateException(String message) {
        super(message);
    }
}
