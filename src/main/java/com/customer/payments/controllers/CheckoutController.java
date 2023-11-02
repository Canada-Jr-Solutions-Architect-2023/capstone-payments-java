package com.customer.payments.controllers;

import com.customer.payments.feign.util.FeignServiceUtil;
import com.customer.payments.model.ChargeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
public class CheckoutController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;
    @Autowired
    private FeignServiceUtil feignServiceUtil;
    private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        logger.info("Fetching premium for payment from carehub quotes api.");
        model.addAttribute("amount", feignServiceUtil.getPremiumForPayment().intValue()); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }
}
