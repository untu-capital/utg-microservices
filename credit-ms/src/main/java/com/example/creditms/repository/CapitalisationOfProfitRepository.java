package com.example.creditms.repository;

import com.example.creditms.entity.CapitalisationOfProfit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CapitalisationOfProfitRepository extends JpaRepository<CapitalisationOfProfit, String> {
    List<CapitalisationOfProfit> findCapitalisationOfProfitsByLoanId(String loanId);
}
