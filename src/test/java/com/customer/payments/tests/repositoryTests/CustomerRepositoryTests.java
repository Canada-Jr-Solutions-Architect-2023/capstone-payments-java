package com.information.customer.tests.repositoryTests;

import com.information.customer.model.Customer;
import com.information.customer.repositories.CustomerRepository;
import com.information.customer.tests.testUtils.CustomerListUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CustomerRepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void saveCustomersInDb() {
        Customer customer = CustomerListUtility.getCustomerlist().get(0);
        Customer savedCustomer = testEntityManager.persistAndFlush(customer);
        assertNotNull(savedCustomer.getCustomerId());
    }

    @Test
    public void getCustomersByFirstName() {
        CustomerListUtility.getCustomerlist().forEach(customer -> testEntityManager.persist(customer));
        List<Customer> customerList = customerRepository.findByFirstName("Veino");
        assertAll(() -> assertEquals(1, customerList.size()),
                () -> assertEquals("Veino", customerList.get(0).getFirstName())
        );
    }

    @Test
    public void getAllCustomersFromDB() {
        testEntityManager.persistAndFlush(CustomerListUtility.getCustomerlist().get(0));
        testEntityManager.persistAndFlush(CustomerListUtility.getCustomerlist().get(1));

        List<Customer> allCustomers = customerRepository.findAll();

        assertEquals(2, allCustomers.size());
        assertAll(
                () -> assertEquals("Veino", allCustomers.get(0).getFirstName()),
                () -> assertEquals("Armstrong", allCustomers.get(0).getLastName()),
                () -> assertEquals("1234567890", allCustomers.get(0).getPhoneNumber())
        );
        assertAll(
                () -> assertEquals("Bill", allCustomers.get(1).getFirstName()),
                () -> assertEquals("Male", allCustomers.get(1).getGender()),
                () -> assertEquals("1234567890", allCustomers.get(1).getPhoneNumber())
        );
    }
}
