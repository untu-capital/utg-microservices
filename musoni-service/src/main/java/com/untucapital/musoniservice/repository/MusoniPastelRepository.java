package com.untucapital.musoniservice.repository;

import com.untucapital.musoniservice.model.MusoniPastel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusoniPastelRepository extends JpaRepository<MusoniPastel, String> {

    @Override
    List<MusoniPastel> findAll();

    MusoniPastel findByTransactionId(String transactionId);

    List<MusoniPastel> findBySynced(String synced);
}
