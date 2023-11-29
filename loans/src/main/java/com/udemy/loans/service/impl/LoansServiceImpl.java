package com.udemy.loans.service.impl;

import com.udemy.loans.constants.LoansConstants;
import com.udemy.loans.dto.LoansDTO;
import com.udemy.loans.entity.Loans;
import com.udemy.loans.exception.LoanAlreadyExistsException;
import com.udemy.loans.exception.ResourceNotFoundException;
import com.udemy.loans.mapper.LoansMapper;
import com.udemy.loans.repository.LoansRepository;
import com.udemy.loans.service.ILoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoansServiceImpl implements ILoansService {

    @Autowired
    private LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoan = loansRepository.findByMobileNumber(mobileNumber);
        if(optionalLoan.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        else {
            loansRepository.save(createNewLoan(mobileNumber));
        }
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public LoansDTO fetchLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoansMapper.mapToLoansDto(loans, new LoansDTO());
    }

    @Override
    public boolean updateLoan(LoansDTO loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "loanNumber", loansDto.getLoanNumber())
        );
        LoansMapper.mapToLoans(loansDto, loans);
        loansRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loansRepository.delete(loans);
        return true;
    }
}
