package com.customer.payments.tests;

import com.customer.payments.model.Card;
import com.customer.payments.model.Payment;
import com.customer.payments.model.PaymentStatus;
import com.customer.payments.tests.testUtils.PaymentListUtility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PaymentTests {

    @Test
    public void getPaymentTest() {
        Payment payment = new Payment((long)1, 5000, PaymentStatus.valueOf("SUCCESSFUL"), "13-12-2023", new Card("Bill", "5675768657", 12, 2030, 123,"", "", 0, 0, 0));
        String expected = String.valueOf(PaymentListUtility.getPaymentlist().get(0).getPaymentStatus());
        assertEquals(expected, String.valueOf(payment.getPaymentStatus()));
    }
}

