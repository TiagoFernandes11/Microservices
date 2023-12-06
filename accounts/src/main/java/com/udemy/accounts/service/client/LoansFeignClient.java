package com.udemy.accounts.service.client;

import com.udemy.accounts.dto.CardsDTO;
import com.udemy.accounts.dto.LoansDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<LoansDTO> fetchLoansDetails(@RequestParam String mobileNumber);
}