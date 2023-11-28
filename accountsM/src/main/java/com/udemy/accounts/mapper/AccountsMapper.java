package com.udemy.Accounts.mapper;

import com.udemy.Accounts.dto.AccountsDTO;
import com.udemy.Accounts.entity.Account;

public class AccountsMapper {

    public static AccountsDTO mapToAccountsDto(Account account, AccountsDTO accountsDTO){
        accountsDTO.setAccountNumber(accountsDTO.getAccountNumber());
        accountsDTO.setAccountType(account.getAccountType());
        accountsDTO.setBranchAddress(accountsDTO.getBranchAddress());
        return accountsDTO;
    }

    public static Account matToAccounts(AccountsDTO accountsDTO, Account account){
        account.setAccountNumber(accountsDTO.getAccountNumber());
        account.setAccountType(accountsDTO.getAccountType());
        account.setBranchAddress(accountsDTO.getBranchAddress());
        return account;
    }
}
