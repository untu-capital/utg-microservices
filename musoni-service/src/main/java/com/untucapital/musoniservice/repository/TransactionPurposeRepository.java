package com.untucapital.musoniservice.repository;

import com.untucapital.musoniservice.model.TransactionPurpose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author tjchidanika
 * @created 5/10/2023
 */
@Repository
public interface TransactionPurposeRepository extends JpaRepository<TransactionPurpose, Integer> {

    Optional<TransactionPurpose> findByName(String name);
}
