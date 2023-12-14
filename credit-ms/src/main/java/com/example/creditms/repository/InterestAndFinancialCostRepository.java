package com.example.creditms.repository;

import com.example.creditms.entity.InterestAndFinancialCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestAndFinancialCostRepository extends JpaRepository<InterestAndFinancialCost, String> {
    List<InterestAndFinancialCost> findInterestAndFinancialCostByLoanId(String loanId);
}
