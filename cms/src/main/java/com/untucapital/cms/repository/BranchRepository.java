package com.untucapital.cms.repository;

import com.untucapital.cms.entity.Branches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branches, String> {
    void deleteById(String id);
    Branches findBranchesById(String id);

    Branches findBranchesByBranchName(String name);
}

