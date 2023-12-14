package com.untucapital.securityserver.service;

import com.untucapital.securityserver.entity.Role;
import com.untucapital.securityserver.entity.User;
import com.untucapital.securityserver.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class UserRoleService extends AbstractService<Role> {

    private static final Logger log = LoggerFactory.getLogger(UserRoleService.class);

    private final RoleRepository roleRepository;

    public UserRoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    protected JpaRepository<Role, String> getRepository() {
        return roleRepository;
    }

    @Override
    public List<User> getUserByRole(String name) {
        return null;
    }
}
