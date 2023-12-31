package com.customer.payments.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId")
    private Long paymentId;
    @Column(name = "policyId")
    private Long policyId;
    @Column(name = "amount")
    private int amount;
    @Convert(converter = StatusConverter.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "payment-status"/*, columnDefinition = "SUCCESSFUL"*/)
    private PaymentStatus paymentStatus;
    @Column(name = "paymentDate")
    private String paymentDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    private Card card;

    public Payment() {

    }

    public Payment(Long policyId, int amount, PaymentStatus paymentStatus, String paymentDate, Card card) {
        this.policyId = policyId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.card = card;
    }
}
