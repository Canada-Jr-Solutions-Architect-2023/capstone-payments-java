package com.customer.payments.feign.controller;


import com.customer.payments.feign.util.FeignServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/paymentsFeign")
public class FeignController {
    @Autowired
    private FeignServiceUtil feignServiceUtil;

    @GetMapping("/policy/getPremiumForPayment/5")
    public Double getPremiumForPayment(@RequestHeader(value = "Authorization", required = true) String headers){
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        /*RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        headers.add("Authorization","Bearer "+ principal.getTokenValue());*/
        headers =  principal.getTokenValue();
        return feignServiceUtil.getPremiumForPayment(headers);
    }

}