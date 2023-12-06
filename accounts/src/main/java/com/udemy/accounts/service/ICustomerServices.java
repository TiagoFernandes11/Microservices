package com.udemy.accounts.service;

import com.udemy.accounts.dto.CustomerDetailsDTO;

public interface ICustomerServices {

    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber);
}
