package com.example.creditms.repository;

import com.example.creditms.entity.CurrentAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrentAssetsRepository extends JpaRepository<CurrentAsset, String> {
    List<CurrentAsset> findCurrentAssetByLoanId(String loanId);
}
