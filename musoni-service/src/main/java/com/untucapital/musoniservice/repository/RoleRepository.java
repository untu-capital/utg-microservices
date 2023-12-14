package com.untucapital.musoniservice.repository;

import com.untucapital.musoniservice.enums.RoleType;
import com.untucapital.musoniservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Chirinda Nyasha Dell 23/11/2021
 */


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByName(RoleType name);
}
