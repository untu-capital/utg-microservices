package com.untucapital.securityserver.repository;

import com.untucapital.securityserver.entity.Role;
import com.untucapital.securityserver.enums.RoleType;
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
