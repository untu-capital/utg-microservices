package com.untucapital.purchaseorder.repository;

import com.untucapital.purchaseorder.enums.ApprovalStatus;
import com.untucapital.purchaseorder.model.POSSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@Repository
public interface SupplierRepository extends JpaRepository<POSSupplier, Integer> {
    List<POSSupplier> findByStatus(ApprovalStatus status);


    Optional<POSSupplier> findByNameAndPhone(String name, String phone);
}
