package com.customer.payments.tests.testUtils;

import com.customer.payments.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentList {
    private String creditCardHolderName;
    private String debitCardHolderName;
    private List<Payment> paymentList;

    public String getCreditCardHolderName() {
        return creditCardHolderName;
    }

    public void setCreditCardHolderName(String creditCardHolderName) {
        this.creditCardHolderName = creditCardHolderName;
    }

    public String getDebitCardHolderName() {
        return debitCardHolderName;
    }

    public void setDebitCardHolderName(String debitCardHolderName) {
        this.debitCardHolderName = debitCardHolderName;
    }

    public PaymentList() {
        paymentList = new ArrayList<Payment>();
    }

    public List<Payment> getPaymentlist() {
        return paymentList;
    }

    public void addPayment(Payment payment) {
        paymentList.add(payment);
    }

    public void removePayment(Payment payment) {
        paymentList.remove(payment);
    }

}
