package com.customer.payments.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StatusConverter implements AttributeConverter<PaymentStatus, String> {
    @Override
    public String convertToDatabaseColumn(PaymentStatus paymentStatus){
        return paymentStatus.toString();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String paymentStatus){
        return PaymentStatus.valueOf(paymentStatus);
    }
}