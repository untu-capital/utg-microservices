package com.untucapital.cms.repository;


import com.untucapital.cms.entity.PettyCashPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */

@Repository
public interface PettyCashPaymentsRepository extends JpaRepository<PettyCashPayments, String> {
}
