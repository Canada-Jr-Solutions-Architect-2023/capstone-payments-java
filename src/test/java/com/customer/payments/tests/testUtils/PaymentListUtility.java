package com.customer.payments.tests.testUtils;

import com.information.customer.model.Address;
import com.information.customer.model.Customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerListUtility {
    private static CustomerList customerList;
    private static List<Customer> customers = new ArrayList<Customer>(Arrays.asList(
            new Customer("Veino", "Armstrong", "13-05-1975", "Female", "1234567890", new Address("East Street", "Halifax", "Nova Scotia", "B3H1R1", "Canada")),
            new Customer("Bill", "Robertson", "14-05-1975", "Male", "1234567890", new Address("West Street", "Truro", "Nova Scotia", "B3H1R2", "Canada")),
            new Customer("Gill", "Christ", "14-05-1984", "Male", "1234567890", new Address("North Street", "Sydney", "Nova Scotia", "B3H1R3", "Canada")),
            new Customer("Harry", "Potter", "14-05-1990", "Male", "1234567890", new Address("South Street", "Yarmouth", "Nova Scotia", "B3H1R4", "Canada")),
            new Customer("Austin", "Dawn", "13-05-1975", "Female", "1234567890", new Address("East Part Street", "KentVille", "Nova Scotia", "B3H1R5", "Canada")),
            new Customer("Justin", "Kara", "06-05-1990", "Female", "1234567890", new Address("South Park Street", "Bridgewater", "Nova Scotia", "B3H1R6", "Canada"))
    ));

    public static List<Customer> getCustomerlist() {
        return customers;
    }

    public static CustomerList getTestCustomerList() {
        customerList = new CustomerList();
        customerList.setFirstName("Harper");
        customerList.setLastName("Lee");
        customers.forEach((customer) -> customerList.addCustomer(customer));
        return customerList;
    }

    public static Customer updateCustomer() {
        Customer customer = new Customer();
        Address address = new Address();
        address.setId((long) 5);
        address.setStreet("South Street");
        address.setCity("Truro");
        address.setState("NS");
        address.setCountry("CA");
        address.setPostalCode("B3J1R5");
        customer.setCustomerId((long) 5);
        customer.setAddress(address);
        customer.setGender("Male");
        customer.setLastName("Armstrong");
        customer.setFirstName("Niel");
        customer.setDateOfBirth("1970-06-18");
        customer.setPhoneNumber("1234567890");
        return customer;
    }
}
