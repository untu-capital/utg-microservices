package com.example.creditms.repository;

import com.example.creditms.entity.ShortTermLiability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortTermLiabilityRepository extends JpaRepository<ShortTermLiability, String> {
    List<ShortTermLiability> findShortTermLiabilityByLoanId(String loanId);
}
