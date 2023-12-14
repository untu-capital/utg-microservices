package com.untucapital.cms.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.untucapital.cms.dto.AuthorisationRequest;
import com.untucapital.cms.dto.messaging.*;
import com.untucapital.cms.dto.sms.SmsRequest;
import com.untucapital.cms.dto.sms.SmsResponse;
import com.untucapital.cms.entity.Authorisation;
import com.untucapital.cms.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Panashe Rutimhu Dell 8/10/2023
 */
@Service
@Slf4j
public class RestClient {

    @Value("${notification.url}")
    private String emailUrl;

    @Value("${auth.url}")
    private String authUrl;

    @Value("${notification.url}")
    private String smsUrl;

    @Value("${musoni.username}")
    private String username;

    @Value("${musoni.X_API_KEY}")
    private String apiKey;

    @Value("${musoni.password}")
    private String password;

    @Value("${musoni.X_FINERACT_PLATFORM_TENANTID}")
    private String xFineractPlatformTenantId;

    private final RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public RestClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
    }

    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(username, password);
        headers.set("X-Fineract-Platform-TenantId", xFineractPlatformTenantId);
        headers.set("x-api-key", apiKey);
        headers.set("Content-Type", "application/json");

        return headers;
    }


    private HttpEntity<String> setHttpEntity() {
        return new HttpEntity<String>(httpHeaders());
    }


    public SendEmailResponse send(SendEmailRequest request) {

        SendEmailResponse response = new SendEmailResponse();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<SendEmailRequest> requestEntity = new HttpEntity<>(request, headers);


        try {
            ResponseEntity<String> res = restTemplate.postForEntity(emailUrl + request, requestEntity, String.class);

            log.info("<<<EMAIL SENT>>>: {}", res);

            //TODO : Make the sms response object return the response code and message
            response = objectMapper.readValue((DataInput) res, SendEmailResponse.class);
        } catch (Exception e) {
            log.info("Failed to get vault", e.getMessage());
        }

        return response;
    }

    public SmsResponse sendSingle(String phone, String message) {

        SmsResponse response = new SmsResponse();
        SmsRequest request = new SmsRequest();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        request.setPhoneNumber(phone);
        request.setMessage(message);

        HttpEntity<SmsRequest> requestEntity = new HttpEntity<>(request, headers);


        try {
            ResponseEntity<String> res = restTemplate.postForEntity(smsUrl + phone + "/" + message, requestEntity, String.class);

            log.info("<<<SMS RESPONSE>>>: {}", res);

            //TODO : Make the sms response object return the response code and message
            response = objectMapper.readValue((DataInput) res, SmsResponse.class);
        } catch (Exception e) {
            log.info("Failed to get vault", e.getMessage());
        }

        return response;
    }

    public String approvalMessage(EmailRequest request) {

        EmailResponse response = new EmailResponse();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<EmailRequest> requestEntity = new HttpEntity<>(request, headers);


        try {
            String res = restTemplate.postForObject(emailUrl + request, requestEntity, String.class);

            log.info("<<<USERS>>>: {}", res);

            //TODO : Make the sms response object return the response code and message
            return res;


        } catch (Exception e) {
            log.info("Failed to get vault", e.getMessage());
            return String.valueOf(e);
        }

    }

    public String buildConfirmationEmail(ConfirmationEmailRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<ConfirmationEmailRequest> requestEntity = new HttpEntity<>(request, headers);


        try {
            String res = restTemplate.postForObject(emailUrl + request, requestEntity, String.class);

            log.info("<<<USERS>>>: {}", res);

            //TODO : Make the sms response object return the response code and message
            return res;


        } catch (Exception e) {
            log.info("Failed to get vault", e.getMessage());
            return String.valueOf(e);
        }

    }

    public User findUser(String request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<String> requestEntity = new HttpEntity<>(request, headers);
        User user = new User();


        try {
            user = restTemplate.postForObject(authUrl + request, requestEntity, User.class);

            log.info("<<<USERS>>>: {}", user);

            //TODO : Make the sms response object return the response code and message
            return user;


        } catch (Exception e) {
            log.info("Failed to get vault", e.getMessage());
            return user;
        }

    }

    public List<User> findAllById(List<String> request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<User> userList = new ArrayList<>();

        HttpEntity<List<String>> requestEntity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<User[]> users = restTemplate.postForEntity(authUrl + request, requestEntity, User[].class);

            log.info("<<<USERS>>>: {}", users);


            if (users.getStatusCode().is2xxSuccessful()) {

                User[] usersArray = users.getBody();

                userList = Arrays.asList(usersArray);
            }

        } catch (Exception e) {
            log.info("Failed to get vault", e.getMessage());
            return null;
        }

        return userList;
    }


    //TODO : Create the authorisation controller
    public List<Authorisation> findAllByUserId(String request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<Authorisation> authorisationList = new ArrayList<>();

        HttpEntity<String> requestEntity = new HttpEntity<>(request, headers);


        try {
            ResponseEntity<Authorisation[]> authorisation  = restTemplate.postForEntity(authUrl+"/"+ request, requestEntity, Authorisation[].class);

            log.info("<<<AUTHORIZATION >>>: {}", authorisation);

            if (authorisation.getStatusCode().is2xxSuccessful()) {

                Authorisation[] usersArray = authorisation.getBody();

                authorisationList = Arrays.asList(usersArray);
            }


        } catch (Exception e) {
            log.info("Failed to get vault", e.getMessage());
            return null;
        }

        return authorisationList;
    }

    //TODO : Create the authorisation controller
    public List<Authorisation> findAllByBranchIdAndAuthLevel(String branchId, String authLevel) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

//        AuthorisationRequest request = new AuthorisationRequest();
//        request.setBranchId(branchId);
//        request.setAuthLevel(authLevel);

        List<Authorisation> authorisationList = new ArrayList<>();

        HttpEntity<AuthorisationRequest> requestEntity = new HttpEntity<>(headers);


        try {
            ResponseEntity<Authorisation[]> authorisation  = restTemplate.postForEntity(authUrl+"/"+branchId+"/"+authLevel, requestEntity, Authorisation[].class);

            log.info("<<<AUTHORIZATION >>>: {}", authorisation);

            if (authorisation.getStatusCode().is2xxSuccessful()) {

                Authorisation[] usersArray = authorisation.getBody();

                authorisationList = Arrays.asList(usersArray);
            }


        } catch (Exception e) {
            log.info("Failed to get vault", e.getMessage());
            return null;
        }

        return authorisationList;
    }

}
