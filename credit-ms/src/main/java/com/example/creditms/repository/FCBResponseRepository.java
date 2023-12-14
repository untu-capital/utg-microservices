package com.example.creditms.repository;

import com.example.creditms.entity.fcb.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FCBResponseRepository extends JpaRepository<Response, String> {

}
