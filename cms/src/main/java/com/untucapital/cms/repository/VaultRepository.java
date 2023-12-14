package com.untucapital.cms.repository;


import com.untucapital.cms.entity.Branches;
import com.untucapital.cms.entity.Vault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author tjchidanika
 * @created 27/9/2023
 */

@Repository
public interface VaultRepository extends JpaRepository<Vault, Integer> {

    Optional<Vault> findVaultByBranch_BranchNameAndType(String branchName, String type);

    Optional<Vault> findByAccount(String account);



    List<Vault> findVaultByBranch_BranchName(String branch);
    List<Vault> findByBranch(Branches branch);

    @Query("SELECT SUM(v.currentAmount) " +
            "FROM Vault v " +
            "WHERE v.account = :account")
    BigDecimal findAccountBalanceByAccount(@Param("account") String account);

    @Query("SELECT SUM(v.currentAmount) " + "FROM Vault v " )
    BigDecimal findTotalAccountBalances();
}
