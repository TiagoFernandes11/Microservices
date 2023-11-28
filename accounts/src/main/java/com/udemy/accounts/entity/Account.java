package com.udemy.Accounts.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Entity
public class Account extends BaseEntity{

    private Integer customerId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer accountNumber;

    @Column(length = 100)
    private String accountType;

    @Column(length = 100)
    private String branchAddress;
}
