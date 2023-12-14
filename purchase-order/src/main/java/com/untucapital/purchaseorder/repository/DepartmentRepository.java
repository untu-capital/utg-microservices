package com.untucapital.purchaseorder.repository;


import com.untucapital.purchaseorder.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tjchidanika
 * @created 7/9/2023
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
