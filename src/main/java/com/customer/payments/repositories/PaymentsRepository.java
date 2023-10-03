package com.customer.payments.repositories;


import com.customer.payments.model.Payment;
import com.customer.payments.model.PaymentStatus;
import com.customer.payments.services.PaymentsService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Long> {
    public List<Payment> findByPaymentStatus(PaymentStatus paymentStatus);

    public List<Payment> findByPaymentId(Long paymentId);
}
