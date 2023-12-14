package com.untucapital.musoniservice.repo;

import com.untucapital.musoniservice.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<AccountEntity,Integer> {
    Optional<AccountEntity> findByAccount(String account);


}
