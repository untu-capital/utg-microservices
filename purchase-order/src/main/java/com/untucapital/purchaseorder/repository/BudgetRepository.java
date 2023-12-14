package com.untucapital.purchaseorder.repository;


import com.untucapital.purchaseorder.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@Repository
public interface BudgetRepository extends JpaRepository<Budget, String> {
    List<Budget> findByYear(int year);


}
