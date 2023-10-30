package com.customer.payments.tests.controllerTests;

import com.customer.payments.controllers.PaymentsController;
import com.customer.payments.model.Payment;
import com.customer.payments.model.PaymentStatus;
import com.customer.payments.services.PaymentsService;
import com.customer.payments.tests.testUtils.PaymentListUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(PaymentsController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PaymentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PaymentsService paymentService;

    @Test
    public void postPayment() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/payments/addPayment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PaymentListUtility.getPaymentlist().get(1)))

        ).andDo(MockMvcResultHandlers.print());


        verify(paymentService, times(1)).addPayment(any(Payment.class));
    }

    @Test
    public void getPaymentsByFirstName() throws Exception {
        final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/payments/paymentStatus/{paymentStatus}", PaymentStatus.valueOf("REFUNDED")).contentType(MediaType.APPLICATION_JSON);
        when(paymentService.getPaymentsByPaymentStatus(PaymentStatus.valueOf("REFUNDED"))).thenReturn(PaymentListUtility.getPaymentlist().stream().filter(payment -> payment.getPaymentStatus().equals(PaymentStatus.valueOf("REFUNDED"))).toList());
        mockMvc.perform(builder).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
        verify(paymentService, times(1)).getPaymentsByPaymentStatus(PaymentStatus.valueOf("REFUNDED"));
    }

    @Test
    public void getAllPayments() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/payments/allPayments")
        );
        verify(paymentService, times(1)).getAllPayments();
    }

    @Test
    public void updatePayment() throws Exception {
        Payment payment = PaymentListUtility.getPaymentlist().get(1);
        payment.setPaymentId((long) 1);
        payment.setAmount(6000);
        payment.getCard().setId((long) 1);
        when(paymentService.getPaymentById((long) 1)).thenReturn(payment);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/payments/updatePayment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payment))
        );
        verify(paymentService, times(1)).updatePayment(any(Payment.class));
    }

    @Test
    public void deletePayment() throws Exception {
        Payment payment = PaymentListUtility.getPaymentlist().get(1);
        payment.setPaymentId((long) 2);
        payment.getCard().setId((long) 2);
        when(paymentService.getPaymentById((long) 2)).thenReturn(payment);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/payments/deletePayment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payment))
        );
        verify(paymentService, times(1)).deletePayment(any(Payment.class));
    }

}

