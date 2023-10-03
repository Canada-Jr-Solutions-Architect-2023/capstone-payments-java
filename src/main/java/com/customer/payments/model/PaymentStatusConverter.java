package com.customer.payments.model;

import java.beans.PropertyEditorSupport;

public class PaymentStatusConverter extends PropertyEditorSupport{

    public void setAsText(final String text) throws IllegalArgumentException {
        setValue(PaymentStatus.fromValue(text));
    }

}