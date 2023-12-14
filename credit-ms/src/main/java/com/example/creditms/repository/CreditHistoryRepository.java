package com.example.creditms.repository;

import com.example.creditms.entity.CreditHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditHistoryRepository extends JpaRepository<CreditHistory, String> {
    List<CreditHistory> findByLoanId(String loanId);
}
