package com.customer.payments.tests.testUtils;

import com.information.customer.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerList {
    private String firstName;
    private String lastName;
    private List<Customer> customerList;

    public CustomerList() {
        customerList = new ArrayList<Customer>();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Customer> getCustomerlist() {
        return customerList;
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customerList.remove(customer);
    }


}
