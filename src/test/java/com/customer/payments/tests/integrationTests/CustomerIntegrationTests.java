package com.information.customer.tests.integrationTests;

import com.information.customer.model.Customer;
import com.information.customer.repositories.CustomerRepository;
import com.information.customer.tests.testUtils.CustomerListUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CustomerIntegrationTests {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void postCustomer() {
        Customer customer = CustomerListUtility.getCustomerlist().get(0);
        ResponseEntity<Customer> response = restTemplate.postForEntity("/customers/addCustomer",
                customer, Customer.class);
        Customer savedCustomer = response.getBody();
        assertAll("Customer Posted",
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(savedCustomer.getCustomerId()),
                () -> assertEquals(customer.getFirstName(), savedCustomer.getFirstName()),
                () -> assertEquals(customer.getLastName(), savedCustomer.getLastName())
        );
    }

    @Test
    public void getAllCustomers() {
        customerRepository.saveAll(CustomerListUtility.getCustomerlist());
        ResponseEntity<List<Customer>> response = restTemplate.exchange("/customers/allCustomers", HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>() {
        });
        List<Customer> responseList = response.getBody();
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(6, responseList.size())
        );
        assertAll(
                () -> assertEquals("Veino", responseList.get(0).getFirstName()),
                () -> assertEquals("Armstrong", responseList.get(0).getLastName()),
                () -> assertEquals("1234567890", responseList.get(0).getPhoneNumber())
        );
        assertAll(
                () -> assertEquals("Justin", responseList.get(5).getFirstName()),
                () -> assertEquals("Female", responseList.get(5).getGender()),
                () -> assertEquals("1234567890", responseList.get(5).getPhoneNumber())
        );
    }
}
