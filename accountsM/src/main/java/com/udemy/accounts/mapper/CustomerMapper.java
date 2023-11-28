package com.udemy.Accounts.mapper;

import com.udemy.Accounts.dto.CustomerDTO;
import com.udemy.Accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO mapToCustomerDto(Customer customer, CustomerDTO customerDTO){
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer mapToCustomer(CustomerDTO customerDTO, Customer customer){
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }
}
