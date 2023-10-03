package com.customer.payments.model;

import java.util.Arrays;

public enum PaymentStatus {
    PENDING("PENDING"), SUCCESSFUL("SUCCESSFUL"), FAILED("FAILED"), REFUNDED("REFUNDED");
    private String value;

    private PaymentStatus(String value) {
        this.value = value;
    }

    public static PaymentStatus fromValue(String value) {
        for (PaymentStatus paymentStatus : values()) {
            if (paymentStatus.value.equalsIgnoreCase(value)) {
                return paymentStatus;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
    }

}

