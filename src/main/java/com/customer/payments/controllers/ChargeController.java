package com.customer.payments.controllers;

import com.customer.payments.feign.util.FeignServiceUtil;
import com.customer.payments.model.ChargeRequest;
import com.customer.payments.model.ChargeStatus;
import com.customer.payments.model.PaymentRequest;
import com.customer.payments.services.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ChargeController {

    @Autowired
    private StripeService paymentsService;
    @Autowired
    private FeignServiceUtil feignServiceUtil;
    private ChargeStatus chargeStatus = new ChargeStatus();

    @PostMapping("/charge")
    //@RequestMapping(value="/charge", method = { RequestMethod.POST, RequestMethod.GET })
    public String charge(ChargeRequest chargeRequest, Model model)
            throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        chargeStatus.setStatus(charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }

    @PutMapping("/policy/updatePaymentStatus")
    public ResponseEntity<String> updatePayment(@RequestBody PaymentRequest paymentRequest){
        if(chargeStatus.getStatus() != null && chargeStatus.getStatus().equalsIgnoreCase("succeeded")) {
            return feignServiceUtil.updatePayment(paymentRequest);
        }else {
            return ResponseEntity.ok("Payment not successful!");
        }
    }
}
