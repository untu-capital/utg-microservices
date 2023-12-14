package com.example.creditms.repository;

import com.example.creditms.entity.FinancialInflow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialInflowRepository extends JpaRepository<FinancialInflow, String> {
    List<FinancialInflow> findFinancialInflowByLoanId(String loanId);
}
