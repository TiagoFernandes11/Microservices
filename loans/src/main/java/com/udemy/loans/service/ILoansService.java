package com.udemy.loans.service;

import com.udemy.loans.dto.LoansDTO;

public interface ILoansService {

    void createLoan(String mobileNumber);
    LoansDTO fetchLoan(String mobileNumber);
    boolean updateLoan(LoansDTO loansDto);
    boolean deleteLoan(String mobileNumber);
}
