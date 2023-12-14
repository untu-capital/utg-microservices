package com.untucapital.cms.repository;

import com.untucapital.cms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    boolean existsUserByUsername(String username);

    boolean existsByContactDetailMobileNumber(long mobileNumber);

    boolean existsByContactDetail_EmailAddress(String emailAddress);

    boolean existsByContactDetail_MobileNumber(long mobile);

    User findByContactDetail_EmailAddress(String email);

    User findByContactDetail_MobileNumber(long mobile);

    //ContactDetail findByEmail(String email);

    User findByResetPasswordToken(String token);

    User getUserById(String userId);

    Optional<User> findUserById(String userId);

    User getUserByContactDetail_MobileNumber(long mobileNumber);

    User getUserByContactDetail_EmailAddress(String email);

    List<User> findUsersByBranch(String branchName);

    List<User> findUsersByCmsUser_RoleIsNotNullAndCmsUser_RoleNotLike(String role);

    List<User> findUsersByPoUser_RoleIsNotNullAndPoUser_RoleNotLike(String role);

    List<User> findUsersByBranchNotNull();

    List<User> findUsersByCreditCommitGroup(String creditCommitGroupName);

    List<User> findUsersByBranchIsNull();

}
