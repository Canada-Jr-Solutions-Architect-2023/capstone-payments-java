package com.customer.payments.tests.repositoryTests;

import com.customer.payments.model.Payment;
import com.customer.payments.model.PaymentStatus;
import com.customer.payments.repositories.PaymentsRepository;
import com.customer.payments.tests.testUtils.PaymentListUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PaymentRepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Test
    public void savePaymentsInDb() {
        Payment payment = PaymentListUtility.getPaymentlist().get(0);
        Payment savedPayment = testEntityManager.persistAndFlush(payment);
        assertNotNull(savedPayment.getPaymentId());
    }

    @Test
    public void getPaymentsByStatus() {
        PaymentListUtility.getPaymentlist().forEach(Payment -> testEntityManager.persist(Payment));
        List<Payment> PaymentList = paymentsRepository.findByPaymentStatus(PaymentStatus.valueOf("SUCCESSFUL"));
        assertAll(() -> assertEquals(1, PaymentList.size()),
                () -> assertEquals(PaymentStatus.valueOf("SUCCESSFUL"), PaymentList.get(0).getPaymentStatus())
        );
    }

    @Test
    public void getAllPaymentsFromDB() {
        testEntityManager.persistAndFlush(PaymentListUtility.getPaymentlist().get(0));
        testEntityManager.persistAndFlush(PaymentListUtility.getPaymentlist().get(1));

        List<Payment> allPayments = paymentsRepository.findAll();

        assertEquals(2, allPayments.size());
        assertAll(
                () -> assertEquals((long)1, allPayments.get(0).getPolicyId()),
                () -> assertEquals(PaymentStatus.valueOf("SUCCESSFUL"), allPayments.get(0).getPaymentStatus()),
                () -> assertEquals(5000, allPayments.get(0).getAmount())
        );
    }
}
