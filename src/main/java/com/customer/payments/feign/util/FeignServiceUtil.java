package com.customer.payments.feign.util;

import com.customer.payments.model.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="feign", url="https://carehub-quotes.ee-cognizantacademy.com/api")
public interface FeignServiceUtil {
    @GetMapping("/policy/getPremiumForPayment/2")
    public Double getPremiumForPayment();

    @PutMapping("/policy/updatePaymentStatus")
    public ResponseEntity<String> updatePayment(@RequestBody PaymentRequest paymentRequest);

}
