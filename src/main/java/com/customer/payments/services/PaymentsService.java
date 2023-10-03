package com.customer.payments.services;

import com.customer.payments.model.Payment;
import com.customer.payments.model.PaymentStatus;
import com.customer.payments.model.PaymentStatusConverter;
import com.customer.payments.repositories.PaymentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.List;

@Service
public class PaymentsService {
    private PaymentsRepository paymentsRepository;

    public PaymentsService(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentsRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentsRepository.findById(id).orElse(null);
    }

    public Payment addPayment(Payment payment) {
        return paymentsRepository.save(payment);
    }

    public Payment updatePayment(Payment payment) {
        return paymentsRepository.save(payment);
    }

    public boolean deletePayment(Payment payment) {
        try {
            paymentsRepository.delete(payment);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public List<Payment> getPaymentsByPaymentStatus(PaymentStatus paymentStatus) {
        return paymentsRepository.findByPaymentStatus(paymentStatus);
    }

}
