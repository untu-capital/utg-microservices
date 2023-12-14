package com.example.creditms.repository;


import com.example.creditms.entity.DisbursementTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisbursementTicketRepository extends JpaRepository<DisbursementTicket, String> {
    void deleteById(String id);
}
