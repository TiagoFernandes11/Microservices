package com.udemy.loans.mapper;

import com.udemy.loans.dto.LoansDTO;
import com.udemy.loans.entity.Loans;

import java.util.Date;

public class LoansMapper {

    public static LoansDTO mapToLoansDTO(Loans loans, LoansDTO loansDTO){
        loansDTO.setCustomerId(loans.getCustomerId());
        loansDTO.setStartDt(loans.getStartDt());
        loansDTO.setLoanType(loans.getLoanType());
        loansDTO.setTotalLoan(loans.getTotalLoan());
        loansDTO.setOutstandingAmount(loans.getOutstandingAmount());
        loansDTO.setCreateDt(loans.getCreateDt());
        return loansDTO;
    }

    public static Loans mapToLoansDTO(LoansDTO loansDTO, Loans loans){
        loans.setCustomerId(loansDTO.getCustomerId());
        loans.setStartDt(loansDTO.getStartDt());
        loans.setLoanType(loansDTO.getLoanType());
        loans.setTotalLoan(loansDTO.getTotalLoan());
        loans.setOutstandingAmount(loansDTO.getOutstandingAmount());
        loans.setCreateDt(loansDTO.getCreateDt());
        return loans;
    }

}
