package com.customer.payments.controllers;

import com.customer.payments.model.ChargeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/paymentsFeign")
public class CheckoutController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey/*= System.getenv("STRIPE_PUBLIC_KEY")*/;

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 5 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.CAD);
        return "checkout";
    }
}
