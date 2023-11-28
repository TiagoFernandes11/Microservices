package com.udemy.Accounts.repository;

import com.udemy.Accounts.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByCustomerId(Integer customerId);

    @Transactional
    @Modifying
    void deleteByCustomerId(Integer customerId);
}
