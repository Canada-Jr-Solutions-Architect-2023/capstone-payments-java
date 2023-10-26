package com.customer.payments.feign.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="feign", url="https://carehub-quotes.ee-cognizantacademy.com/api")
public interface FeignServiceUtil {
    @GetMapping("/policy/getPremiumForPayment/2")
    public Double getPremiumForPayment();
}
