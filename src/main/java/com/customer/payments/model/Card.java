package com.customer.payments.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    //debit card details
    @Column(name = "debitCardHolderName")
    private String debitCardHolderName;
    @Column(name = "debitCardNumber")
    private String debitCardNumber;
    @Column(name = "debitExpirationMonth")
    private int debitExpirationMonth;
    @Column(name = "debitExpirationYear")
    private int debitExpirationYear;
    @Column(name = "debitCvv")
    private int debitCvv;

    //credit card details
    @Column(name = "creditCardHolderName")
    private String creditCardHolderName;
    @Column(name = "creditCardNumber")
    private String creditCardNumber;
    @Column(name = "creditExpirationMonth")
    private int creditExpirationMonth;
    @Column(name = "creditExpirationYear")
    private int creditExpirationYear;
    @Column(name = "creditCvv")
    private int creditCvv;

    public Card() {

    }

    public Card(String debitCardHolderName, String debitCardNumber, int debitExpirationMonth, int debitExpirationYear, int debitCvv, String creditCardHolderName, String creditCardNumber, int creditExpirationMonth, int creditExpirationYear, int creditCvv) {
        this.debitCardHolderName = debitCardHolderName;
        this.debitCardNumber = debitCardNumber;
        this.debitExpirationMonth = debitExpirationMonth;
        this.debitExpirationYear = debitExpirationYear;
        this.debitCvv = debitCvv;
        this.creditCardHolderName = creditCardHolderName;
        this.creditCardNumber = creditCardNumber;
        this.creditExpirationMonth = creditExpirationMonth;
        this.creditExpirationYear = creditExpirationYear;
        this.creditCvv = creditCvv;
    }
}
