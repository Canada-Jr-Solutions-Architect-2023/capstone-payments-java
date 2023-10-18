package com.customer.payments.tests.serviceTests;

import com.customer.payments.model.Payment;
import com.customer.payments.repositories.PaymentsRepository;
import com.customer.payments.services.PaymentsService;
import com.customer.payments.tests.testUtils.PaymentListUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTests {

    @Mock
    private PaymentsRepository paymentsRepository;

    @InjectMocks
    private PaymentsService paymentsService;

    @Test
    public void addPayment() {
        Payment expectedPayment = PaymentListUtility.getPaymentlist().get(0);
        when(paymentsRepository.save(expectedPayment)).thenReturn(expectedPayment);
        Payment resultPayment = paymentsService.addPayment(expectedPayment);
        assertEquals(expectedPayment, resultPayment);
        verify(paymentsRepository, times(1)).save(any(Payment.class));
    }

    @Test
    public void getAllPayments() {
        List<Payment> expectedPaymentList = PaymentListUtility.getPaymentlist();
        when(paymentsRepository.findAll()).thenReturn(expectedPaymentList);
        List<Payment> resultPaymentList = paymentsService.getAllPayments();
        assertEquals(expectedPaymentList.size(), resultPaymentList.size());
        assertEquals(expectedPaymentList, resultPaymentList);
        verify(paymentsRepository, times(1)).findAll();
    }

    @Test
    public void updatePayment() {
        Payment existingPayment = PaymentListUtility.getPaymentlist().get(1);
        existingPayment.setPaymentId((long) 1);
        existingPayment.getCard().setId((long) 1);
        existingPayment.setAmount(6000);
        when(paymentsRepository.save(existingPayment)).thenReturn(existingPayment);
        Payment updatedPayment = paymentsService.updatePayment(existingPayment);
        assertEquals(existingPayment.getPaymentId(), updatedPayment.getPaymentId());
    }

    @Test
    public void deletePayment() {
        Payment existingPayment = PaymentListUtility.getPaymentlist().get(1);
        existingPayment.setPaymentId((long) 1);
        paymentsService.deletePayment(existingPayment);
        verify(paymentsRepository, times(1)).delete(existingPayment);
    }

}
