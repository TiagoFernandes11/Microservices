package com.udemy.accounts.service.client;

import com.udemy.accounts.dto.LoansDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient{

    @Override
    public ResponseEntity<LoansDTO> fetchLoansDetails(String mobileNumber) {
        return null;
    }
}
