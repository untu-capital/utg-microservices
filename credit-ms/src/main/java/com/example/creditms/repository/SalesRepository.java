package com.example.creditms.repository;

import com.example.creditms.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, String> {
    List<Sales> findSalestByLoanIdAndBusinessUnitOrderByMonthAsc(String id, String businessUnit);


}
