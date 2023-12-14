package com.example.creditms.repository;

import com.example.creditms.entity.Branches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branches, String> {
    void deleteById(String id);
    Branches findBranchesById(String id);

    Branches findBranchesByBranchName(String name);
}

