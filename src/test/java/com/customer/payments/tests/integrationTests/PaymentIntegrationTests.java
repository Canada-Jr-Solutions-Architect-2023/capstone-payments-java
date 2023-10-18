package com.customer.payments.tests.integrationTests;

import com.customer.payments.model.Payment;
import com.customer.payments.model.PaymentStatus;
import com.customer.payments.repositories.PaymentsRepository;

import com.customer.payments.tests.testUtils.PaymentListUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class PaymentIntegrationTests {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PaymentsRepository paymentsRepository;

    @Test
    public void postPayment() {
        Payment payment = PaymentListUtility.getPaymentlist().get(0);
        ResponseEntity<Payment> response = restTemplate.postForEntity("/payments/addPayment",
                payment, Payment.class);
        Payment savedPayment = response.getBody();
        assertAll("Payment Posted",
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(savedPayment.getPaymentId()),
                () -> assertEquals(payment.getPaymentStatus(), savedPayment.getPaymentStatus()),
                () -> assertEquals(payment.getAmount(), savedPayment.getAmount())
        );
    }

    @Test
    public void getAllPayments() {
        paymentsRepository.saveAll(PaymentListUtility.getPaymentlist());
        ResponseEntity<List<Payment>> response = restTemplate.exchange("/payments/allPayments", HttpMethod.GET, null, new ParameterizedTypeReference<List<Payment>>() {
        });
        List<Payment> responseList = response.getBody();
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(6, responseList.size())
        );
        assertAll(
                () -> assertEquals((long)1, responseList.get(0).getPolicyId()),
                () -> assertEquals(PaymentStatus.valueOf("SUCCESSFUL"), responseList.get(0).getPaymentStatus()),
                () -> assertEquals(5000, responseList.get(0).getAmount())
        );
    }
}
