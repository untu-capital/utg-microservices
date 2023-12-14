package com.untucapital.securityserver.service;


import com.untucapital.securityserver.auth.UserPrincipal;
import com.untucapital.securityserver.entity.User;
import com.untucapital.securityserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@Service
@Transactional
public class UserPrincipalService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserPrincipalService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsernameOrMobileNumber(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return UserPrincipal.create(user);
    }

    public Optional<User> findUserByUsernameOrMobileNumber(String username) {
        try {
            Long mobileNumber = Long.parseLong(username);
            return Optional.ofNullable(userRepository.findByContactDetail_MobileNumber(mobileNumber));
        } catch (NumberFormatException e) {
            return userRepository.findByUsername(username);
        }
    }

    // This method is used by the AuthFilter
    public UserPrincipal loadUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        return UserPrincipal.create(user);
    }
}
