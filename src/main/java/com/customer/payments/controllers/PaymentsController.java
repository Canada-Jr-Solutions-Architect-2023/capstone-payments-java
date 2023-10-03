package com.customer.payments.controllers;

import com.customer.payments.model.Payment;
import com.customer.payments.model.PaymentStatus;
import com.customer.payments.model.PaymentStatusConverter;
import com.customer.payments.services.PaymentsService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

    public PaymentsController(PaymentsService paymentService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping("/allPayments")
    public List<Payment> getAllPayments() {
        return this.paymentsService.getAllPayments();
    }

    @GetMapping("/paymentStatus/{paymentStatus}")
    public List<Payment> getPaymentsByPaymentStatus(@PathVariable PaymentStatus paymentStatus) {
        return this.paymentsService.getPaymentsByPaymentStatus(paymentStatus);
    }

    @PostMapping("/addPayment")
    public Payment addPayment(@RequestBody Payment payment) {
        return this.paymentsService.addPayment(payment);
    }

    @PutMapping("/updatePayment")
    public ResponseEntity putPayment(@RequestBody Payment payment) {
        if (payment.getPaymentId() == null) {
            return new ResponseEntity("Entity requires Id for Update", HttpStatus.BAD_REQUEST);
        } else if (this.paymentsService.getPaymentById(payment.getPaymentId()) == null) {
            return new ResponseEntity("Entity with given ID not found", HttpStatus.BAD_REQUEST);
        } else {
            this.paymentsService.updatePayment(payment);
            return new ResponseEntity(payment, HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("/deletePayment")
    public ResponseEntity<String> deletePayment(@RequestBody Payment payment) {
        if (payment.getPaymentId() == null) {
            return new ResponseEntity("Entity requires Id for Delete", HttpStatus.BAD_REQUEST);
        } else if (this.paymentsService.getPaymentById(payment.getPaymentId()) == null) {
            return new ResponseEntity("Entity with given ID not found", HttpStatus.BAD_REQUEST);
        } else {
            this.paymentsService.deletePayment(payment);
            return ResponseEntity.ok("Payment deleted successfully!");
        }
    }

    @DeleteMapping("/deletePendingPayments")
    public ResponseEntity<String> deletePendingPayments() {
        List<Payment> deletePaymentList = paymentsService.getPaymentsByPaymentStatus(PaymentStatus.valueOf("PENDING"));
        deletePaymentList.forEach(deletePayment -> {
            this.paymentsService.deletePayment(deletePayment);
        });
        if (!deletePaymentList.isEmpty()) {
            return ResponseEntity.ok("Pending Payments deleted successfully");
        } else if (deletePaymentList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("deletePaymentList is empty");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("deletePaymentList failed");
        }
    }

    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(PaymentStatus.class, new PaymentStatusConverter());
    }
}
