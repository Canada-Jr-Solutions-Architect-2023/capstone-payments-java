package com.information.customer.tests;

import com.information.customer.model.Address;
import com.information.customer.model.Customer;
import com.information.customer.tests.testUtils.CustomerListUtility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CustomerTests {

    @Test
    public void getCustomerTest() {
        Customer customer = new Customer("Veino", "Armstrong", "13-05-1975", "Female", "1234567890", new Address("East Street", "Halifax", "Nova Scotia", "B3H1R1", "Canada"));
        String expected = CustomerListUtility.getCustomerlist().get(0).getFirstName();
        assertEquals(expected, customer.getFirstName());
    }
}
