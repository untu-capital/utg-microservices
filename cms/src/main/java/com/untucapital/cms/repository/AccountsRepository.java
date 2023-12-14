package com.untucapital.cms.repository;

import com.untucapital.cms.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<AccountEntity,Integer> {
    Optional<AccountEntity> findByAccount(String account);


}
