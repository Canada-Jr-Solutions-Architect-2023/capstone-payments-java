package com.information.customer.tests.serviceTests;

import com.information.customer.model.Customer;
import com.information.customer.repositories.CustomerRepository;
import com.information.customer.services.CustomerService;
import com.information.customer.tests.testUtils.CustomerListUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void addCustomer() {
        Customer expectedCustomer = CustomerListUtility.getCustomerlist().get(0);
        when(customerRepository.save(expectedCustomer)).thenReturn(expectedCustomer);
        Customer resultCustomer = customerService.addCustomer(expectedCustomer);
        assertEquals(expectedCustomer, resultCustomer);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void getAllCustomers() {
        List<Customer> expectedCustomerList = CustomerListUtility.getCustomerlist();
        when(customerRepository.findAll()).thenReturn(expectedCustomerList);
        List<Customer> resultCustomerList = customerService.getAllCustomers();
        assertEquals(expectedCustomerList.size(), resultCustomerList.size());
        assertEquals(expectedCustomerList, resultCustomerList);
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void updateCustomer() {
        Customer existingCustomer = CustomerListUtility.getCustomerlist().get(1);
        existingCustomer.setCustomerId((long) 1);
        existingCustomer.getAddress().setId((long) 1);
        existingCustomer.setPhoneNumber("13245678");
        when(customerRepository.save(existingCustomer)).thenReturn(existingCustomer);
        Customer updatedCustomer = customerService.updateCustomer(existingCustomer);
        assertEquals(existingCustomer.getCustomerId(), updatedCustomer.getCustomerId());
    }

    @Test
    public void deleteCustomer() {
        Customer existingCustomer = CustomerListUtility.getCustomerlist().get(1);
        existingCustomer.setCustomerId((long) 1);
        customerService.deleteCustomer(existingCustomer);
        verify(customerRepository, times(1)).delete(existingCustomer);
    }

}
