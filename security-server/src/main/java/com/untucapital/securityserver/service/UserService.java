package com.untucapital.securityserver.service;

import com.untucapital.securityserver.auth.TokenProvider;
import com.untucapital.securityserver.auth.UserPrincipal;
import com.untucapital.securityserver.dto.payload.LoginReq;
import com.untucapital.securityserver.dto.payload.LoginResp;
import com.untucapital.securityserver.dto.payload.SignUpRequest;
import com.untucapital.securityserver.entity.*;
import com.untucapital.securityserver.enums.RoleType;
import com.untucapital.securityserver.exception.ResourceNotFoundException;
import com.untucapital.securityserver.exception.UntuSuiteException;
import com.untucapital.securityserver.repository.ConfirmationTokenRepository;
import com.untucapital.securityserver.repository.RoleRepository;
import com.untucapital.securityserver.repository.UserRepository;
import com.untucapital.securityserver.utils.EmailSender;
import com.untucapital.securityserver.utils.EmailValidator;
import com.untucapital.securityserver.utils.FormatterUtil;
import com.untucapital.securityserver.utils.RandomNumUtils;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@jakarta.transaction.Transactional
@Service
@RequiredArgsConstructor
public class UserService extends AbstractService<User> {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final EmailValidator emailValidator;
    private final RoleRepository roleRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailSender emailSender;

    private final SmsService smsService;

    @Override
    protected JpaRepository<User, String> getRepository() {
        return userRepository;
    }

    @Override
    @Transactional(value = "transactionManager")
    public List<User> getUserByRole(String name) {
        return null;
    }

//    public Optional<LoginResp> authenticateUser(LoginReq loginReq) {
//        log.debug("User Authentication Request - {}", FormatterUtil.toJson(loginReq));
//
//        validateUserStatus(loginReq.getUsername());
//
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
//
//        Optional<LoginResp> loginRespOptional = Optional.empty();
//        if (authentication != null) {
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            String accessToken = tokenProvider.generateToken(authentication);
//            loginRespOptional = Optional.of(
//                    new LoginResp(accessToken, ((UserPrincipal) authentication.getPrincipal()).getId())
//            );
//        }
//        return loginRespOptional;
//    }


    public Optional<LoginResp> authenticateUser(LoginReq loginReq) {
        log.debug("User Authentication Request - {}", FormatterUtil.toJson(loginReq));

        validateUserStatus(loginReq.getUsername());

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));

        Optional<LoginResp> loginRespOptional = Optional.empty();
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String accessToken = tokenProvider.generateToken(authentication);

            // Get the user ID from the UserPrincipal
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            String userId = userPrincipal.getId();

            // Fetch the user
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();

                // Create a default CmsUser if it's null
                CmsUser cmsUser = user.getCmsUser();
                if (cmsUser == null) {
                    cmsUser = new CmsUser();
                    cmsUser.setRole(""); // Set the role or any other necessary fields
                    user.setCmsUser(cmsUser);
                    userRepository.save(user);
                }

                // Create the LoginResp
                loginRespOptional = Optional.of(new LoginResp(accessToken, userId));
            }
        }
        return loginRespOptional;
    }


    private void validateUserStatus(String username) throws SecurityException {

        String failedValidationMsg = "Login failed : Invalid Username or Password!";

        Optional<User> optionalUser = findUserByUsernameOrMobileNumber(username);
        if (optionalUser.isEmpty()) {
            log.error("User with username: {}, does not exist.", username);
            throw new SecurityException(failedValidationMsg);
        }

        User user = optionalUser.get();
        if (!user.isActive()) {
            log.error("User with username: {}, is currently not active.", username);
            throw new SecurityException(failedValidationMsg);
        }

        if (!user.isVerified()) {
            log.error("User with username: {}, is not verified.", username);
            throw new SecurityException(failedValidationMsg);
        }

        log.debug("User Status validation complete for User: {}", user.getId());
    }

    @Transactional(value = "transactionManager")
    public Optional<String> registerUser(SignUpRequest signUpRequest) {
        log.debug("User Registration Request - {}", FormatterUtil.toJson(signUpRequest));

        if (userRepository.existsUserByUsername(signUpRequest.getUsername())) {
            throw new ValidationException("Username is already taken");
        }

        if (userRepository.existsByContactDetailMobileNumber(signUpRequest.getMobileNumber())) {
            throw new ValidationException("Mobile Number already exists");
        }

//        if (!emailValidator.test(signUpRequest.getEmail())) {
//            throw new ValidationException("Email Address not valid");
//        }

//        if (userRepository.existsByContactDetail_EmailAddress(signUpRequest.getEmail())) {
//            throw new ValidationException("Email Address already exists");
//        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setActive(true);
        user.setVerified(true);

        ContactDetail cd = new ContactDetail();
        cd.setEmailAddress(signUpRequest.getEmail());
        cd.setMobileNumber(signUpRequest.getMobileNumber());
        user.setContactDetail(cd);

        CmsUser cmsUser = new CmsUser();
        cmsUser.setRole("");
        user.setCmsUser(cmsUser);

        Role userRole = roleRepository.findByName(RoleType.ROLE_CLIENT)
                .orElseThrow(() -> new UntuSuiteException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));

        log.info("Registering new user - {}", FormatterUtil.toJson(user));

        User createdUser = userRepository.save(user);

        // Generate and Save confirmation token
        String token = RandomNumUtils.generateCode(6);

        ConfirmationToken confirmToken = new ConfirmationToken();
        confirmToken.setToken(token);
        confirmToken.setExpirationDate(LocalDateTime.now().plusMinutes(30));
        confirmToken.setUser(createdUser);
        confirmationTokenRepository.save(confirmToken);

        String emailText = emailSender.buildConfirmationEmail(user.getFirstName(), user.getUsername(), token);
        emailSender.send(user.getContactDetail().getEmailAddress(), "Untu Credit Application Account Verification", emailText);

        String smsText = "Your verification code is : " + token +
                "\nYou can use: " + user.getUsername() + " as your username, or login using your mobile number: " + user.getContactDetail().getMobileNumber() + "." +
                "\n\nThank you for registering with us.\nUntu Capital Ltd";
        smsService.sendSingle(String.valueOf(user.getContactDetail().getMobileNumber()), smsText);

        return Optional.of(createdUser.getId());
    }

    private String buildConfirmationEmail(String firstName, String username, String token) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Dear " + firstName + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering on Untu Capital Credit Application. Please click on the below link to activate your account: " +
                "               </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"http://localhost:7878/api/utg/auth/confirm_account?username=" + username + "&code=" + token + "\">Confirm Account</a> </p></blockquote>\n Link will expire in 30 minutes. <p>Cheers</p>\n<p>Untu Credit Application Team</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    private String createRegEmailText(String firstName, String lastName, String username, String code) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n Dear ").append(firstName).append(" ").append(lastName)
                .append(", \n\n Follow the link below to verify your email address \n http://localhost:4200/authentication/account-confirm?username=")
                .append(username)
                .append("&code=").append(code)
                .append("\n\n\n Regards \n Untu Credit Application Team.");
        return sb.toString();
    }

    @Transactional(value = "transactionManager")
    public boolean confirmUserAccount(String username, String verificationCode) {
        log.debug("User Confirmation Request username: {}, verificationCode: {}", username, verificationCode);

        User user = findUserByUsernameOrMobileNumber(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Username", username));

        ConfirmationToken confirmationToken = confirmationTokenRepository.findConfirmationTokenByToken(verificationCode)
                .orElseThrow(() -> new ResourceNotFoundException("ConfirmationToken", "verification code", verificationCode));

        if (!confirmationToken.getToken().equals(verificationCode)) {
            throw new ValidationException("Verification Failed. Invalid verification code");
        }

        if (user.isVerified() || confirmationToken.getDateConfirmed() != null) {
            throw new ValidationException("Verification code already confirmed.");
        }

        if (confirmationToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new ValidationException("Verification Code already expired.");
        }

        log.info("Confirming new User - {}", FormatterUtil.toJson(user));
        user.setVerified(true);
        user.setActive(true);
        userRepository.save(user);

        confirmationToken.setDateConfirmed(LocalDateTime.now());
        confirmationTokenRepository.save(confirmationToken);
        return true;
    }

    @Transactional(value = "transactionManager")
    public boolean checkUserEmail(String email) {
        log.debug("Check User Email Request email: {}", email);

        return userRepository.existsByContactDetail_EmailAddress(email);
    }

    @Transactional(value = "transactionManager")
    public boolean checkUserMobile(long mobile) {
        log.debug("Check User Mobile Request mobile: {}", mobile);

        return userRepository.existsByContactDetailMobileNumber(mobile);
    }

    @Transactional(value = "transactionManager")
    public void updateResetPasswordToken(String token, String email) throws ResourceNotFoundException {
        User user = userRepository.findByContactDetail_EmailAddress(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("Could not find any %s with the email: ", "user" ,email);
        }
    }

    @Transactional(value = "transactionManager")
    public void updateResetPasswordTokenMobile(String token, long mobile) throws ResourceNotFoundException {
        User user = userRepository.findByContactDetail_MobileNumber(mobile);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("Could not find any %s with the mobile number: ", "user" ,mobile);
        }
    }

    @Transactional(value = "transactionManager")
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    @Transactional(value = "transactionManager")
    public List<User> getUsersByRole(String roleName) {
        List<User> users = userRepository.findAll();
        List<User> usersByRole = users.stream()
                .filter(user ->
                        user.getRoles().stream()
                                .anyMatch(userRole -> roleName.equals(userRole.getDescription())))
                .collect(Collectors.toList());
        return usersByRole;
    }

    @Transactional(value = "transactionManager")
    public Optional<User> findUserByUsernameOrMobileNumber(String username) {
        try {
            Long mobileNumber = Long.parseLong(username);
            return Optional.ofNullable(userRepository.findByContactDetail_MobileNumber(mobileNumber));
        } catch (NumberFormatException e) {
            log.debug("QWERTY USER LOGIN");
            return userRepository.findByUsername(username);
        }
    }

    @Transactional(value = "transactionManager")
    public String deleteUserById(String id){
        confirmationTokenRepository.deleteByUser(id);
        userRepository.deleteById(id);
        return "Deleted Successfully";
    }

    @Transactional(value = "transactionManager")
    public void resetTokenExpired(long mobile) throws ResourceNotFoundException {
        User updateExpiredToken = userRepository.findByContactDetail_MobileNumber(mobile);
        if (updateExpiredToken != null) {
//            user.setResetPasswordToken(token);
//            userRepository.save(user);

//            User createdUser = userRepository.save(user);
            // Generate and Save confirmation token
            String token = RandomNumUtils.generateCode(6);
            ConfirmationToken confirmToken = new ConfirmationToken();
            confirmToken.setToken(token);
            confirmToken.setExpirationDate(LocalDateTime.now().plusMinutes(30));
            confirmToken.setUser(updateExpiredToken);
            confirmationTokenRepository.save(confirmToken);
        } else {
            throw new ResourceNotFoundException("Could not find any %s with the mobile number: ", "user" ,mobile);
        }
    }


    @Transactional(value = "transactionManager")
    public void saveUser(UserPrincipal updatedUserRole) {
    }

    @Transactional(value = "transactionManager")
    public User updateUserCmsUser(String userId, CmsUser defaultCmsUser) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            CmsUser cmsUser = user.getCmsUser();

            if (cmsUser == null) {
                // If the user's cmsUser is null, set it to the defaultCmsUser
                user.setCmsUser(defaultCmsUser);
                userRepository.save(user);
            }
            return user;
        } else {
            // Handle the case where the user with the provided userId is not found.
            throw new ResourceNotFoundException("User", "id", userId);
        }
    }


    //Find All User By
    @Transactional(value = "transactionManager")
    public List<User> findAllById(List<String> userIds){
        return userRepository.findAllById(userIds);
    }


    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority>  authorities = new ArrayList<>();
        for(String role: roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
