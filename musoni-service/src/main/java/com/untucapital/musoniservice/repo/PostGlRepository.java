package com.untucapital.musoniservice.repo;

import com.untucapital.musoniservice.entity.PostGl;
import com.untucapital.musoniservice.entity.res.PostGlResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository
public interface PostGlRepository extends JpaRepository<PostGl, BigInteger> {
    List<PostGlResponseDTO> findByTxDate(Date txDate);

    List<PostGlResponseDTO> findByAccountLink(Integer AccountLink);

    @Query("SELECT SUM(p.Debit) - SUM(p.Credit) " +
            "FROM PostGl p " +
            "WHERE p.accountLink = :accountLink")
    Float findAccountBalanceByAccountLink(@Param("accountLink") Integer accountLink);

}
