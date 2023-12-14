package com.example.creditms.repository;

import com.example.creditms.entity.BankTo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankToRepository extends JpaRepository<BankTo, String> {
    //List<BankTo> findBankToByLoanIdAAndBankOrderByMonthAsc(String loanId, String bank);
    List<BankTo> findBankToByLoanIdAndBankOrderByMonthAsc(String loanId, String bank);
}
