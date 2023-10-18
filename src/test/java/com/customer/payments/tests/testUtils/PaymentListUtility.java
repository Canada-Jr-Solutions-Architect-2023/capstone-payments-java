package com.customer.payments.tests.testUtils;

import com.customer.payments.model.Card;
import com.customer.payments.model.Payment;
import com.customer.payments.model.PaymentStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaymentListUtility {
    private static PaymentList paymentList;
    private static List<Payment> payments = new ArrayList<Payment>(Arrays.asList(
            new Payment((long)1, 5000, PaymentStatus.valueOf("SUCCESSFUL"), "13-12-2023", new Card("Bill", "5675768657", 12, 2030, 123,"", "", 0, 0, 0)),
            new Payment((long)2, 5000, PaymentStatus.valueOf("PENDING"), "13-12-2023", new Card("Justin", "5675768657", 12, 2030, 123,"", "", 0, 0, 0)),
            new Payment((long)3, 5000, PaymentStatus.valueOf("PENDING"), "13-12-2023", new Card("Austin", "5675768657", 12, 2030, 123,"", "", 0, 0, 0)),
            new Payment((long)1, 5000, PaymentStatus.valueOf("FAILED"), "13-12-2023", new Card("Dora", "5675768657", 12, 2030, 123,"", "", 0, 0, 0)),
            new Payment((long)1, 5000, PaymentStatus.valueOf("FAILED"), "13-12-2023", new Card("Katerine", "5675768657", 12, 2030, 123,"", "", 0, 0, 0)),
            new Payment((long)1, 5000, PaymentStatus.valueOf("REFUNDED"), "13-12-2023", new Card("Harry", "5675768657", 12, 2030, 123,"", "", 0, 0, 0))
    ));

    public static List<Payment> getPaymentlist() {
        return payments;
    }

    public static PaymentList getTestPaymentList() {
        paymentList = new PaymentList();
        paymentList.setDebitCardHolderName("Harry");
        paymentList.setCreditCardHolderName("");
        payments.forEach((payment) -> paymentList.addPayment(payment));
        return paymentList;
    }


}
