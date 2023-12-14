package com.untucapital.musoniservice.service;

import com.untucapital.musoniservice.model.Role;
import com.untucapital.musoniservice.model.User;
import com.untucapital.musoniservice.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@jakarta.transaction.Transactional
@Service
public class RoleService extends AbstractService<Role> {

    private static final Logger log = LoggerFactory.getLogger(RoleService.class);

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
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

    @Transactional(value = "transactionManager")
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Transactional(value = "transactionManager")
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
