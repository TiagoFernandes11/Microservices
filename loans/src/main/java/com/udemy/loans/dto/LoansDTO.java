package com.udemy.loans.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LoansDTO {
    private int customerId;

    private Date startDt;

    private String loanType;

    private int totalLoan;

    private int outstandingAmount;

    private String createDt;

}
