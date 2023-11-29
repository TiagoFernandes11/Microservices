package com.udemy.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Account extends BaseEntity{

    private Integer customerId;

    @Id
    private Integer accountNumber;

    @Column(length = 100)
    private String accountType;

    @Column(length = 100)
    private String branchAddress;
}
