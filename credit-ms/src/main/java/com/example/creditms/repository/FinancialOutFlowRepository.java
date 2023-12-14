package com.example.creditms.repository;

import com.example.creditms.entity.FinancialOutFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialOutFlowRepository extends JpaRepository<FinancialOutFlow, String> {
    List<FinancialOutFlow> findFinancialOutFlowByLoanId(String loanId);
}
