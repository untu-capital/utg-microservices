package com.untucapital.musoniservice.repository;

import com.untucapital.musoniservice.model.MusoniClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusoniRepository extends JpaRepository<MusoniClient, String> {

    MusoniClient findMusoniClientById(String clientId);

    List<MusoniClient> findMusoniClientsByStatus(String status);
}
