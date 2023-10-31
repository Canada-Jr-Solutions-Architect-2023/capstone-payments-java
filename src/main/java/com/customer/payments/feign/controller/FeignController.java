package com.customer.payments.feign.controller;


import com.customer.payments.feign.util.FeignServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/paymentsFeign")
public class FeignController {
    @Autowired
    private FeignServiceUtil feignServiceUtil;

    @GetMapping("/policy/getPremiumForPayment/5")
    public Double getPremiumForPayment(){
        return feignServiceUtil.getPremiumForPayment();
    }

}