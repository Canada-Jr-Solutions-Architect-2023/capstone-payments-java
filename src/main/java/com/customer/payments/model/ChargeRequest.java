package com.customer.payments.model;

import lombok.Data;

@Data
public class ChargeRequest {

    public enum Currency {
       CAD, EUR, USD;
    }
    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
}
