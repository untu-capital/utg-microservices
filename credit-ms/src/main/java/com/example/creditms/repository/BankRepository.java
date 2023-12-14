package com.example.creditms.repository;


import com.example.creditms.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank, String> {
    List<Bank> findByLoanId(String loanId);
}
