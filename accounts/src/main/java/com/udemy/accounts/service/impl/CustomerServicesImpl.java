package com.udemy.accounts.service.impl;

import com.udemy.accounts.dto.AccountsDTO;
import com.udemy.accounts.dto.CardsDTO;
import com.udemy.accounts.dto.CustomerDetailsDTO;
import com.udemy.accounts.dto.LoansDTO;
import com.udemy.accounts.entity.Account;
import com.udemy.accounts.entity.Customer;
import com.udemy.accounts.exception.ResourceNotFoundException;
import com.udemy.accounts.mapper.AccountsMapper;
import com.udemy.accounts.mapper.CustomerMapper;
import com.udemy.accounts.repository.AccountsRepository;
import com.udemy.accounts.repository.CustomerRepository;
import com.udemy.accounts.service.ICustomerServices;
import com.udemy.accounts.service.client.CardsFeignClient;
import com.udemy.accounts.service.client.LoansFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServicesImpl implements ICustomerServices {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Autowired
    public CustomerServicesImpl(AccountsRepository accountsRepository, CustomerRepository customerRepository, CardsFeignClient cardsFeignClient, LoansFeignClient loansFeignClient) {
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
        this.cardsFeignClient = cardsFeignClient;
        this.loansFeignClient = loansFeignClient;
    }

    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Account account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDTO customerDetailsDTO = CustomerMapper.mapToCustomerDetailDto(customer, new CustomerDetailsDTO());
        customerDetailsDTO.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDTO()));

        ResponseEntity<LoansDTO> loansDTOResponseEntity = loansFeignClient.fetchLoansDetails(customer.getMobileNumber());
        if(null != loansDTOResponseEntity){
            customerDetailsDTO.setLoansDto(loansDTOResponseEntity.getBody());
        }

        ResponseEntity<CardsDTO> cardsDTOResponseEntity = cardsFeignClient.fetchCardDetails(customer.getMobileNumber());
        if(null != cardsDTOResponseEntity){
            customerDetailsDTO.setCardsDto(cardsDTOResponseEntity.getBody());
        }


        return customerDetailsDTO;
    }
}
