package com.untucapital.securityserver.service;

import com.untucapital.securityserver.dto.req.AuthorisationRequestDTO;
import com.untucapital.securityserver.dto.res.AuthorisationResponseDTO;
import com.untucapital.securityserver.entity.Authorisation;
import com.untucapital.securityserver.entity.User;
import com.untucapital.securityserver.repository.AuthorisationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@jakarta.transaction.Transactional
public class AuthorisationService extends AbstractService<Authorisation> {

    private static final Logger log = LoggerFactory.getLogger(AuthorisationService.class);


    private  final AuthorisationRepository authorisationRepository;


    public AuthorisationService(AuthorisationRepository authorisationRepository) {
        this.authorisationRepository = authorisationRepository;
    }

    @Transactional(value = "transactionManager")
    public void  saveAuthorisation(AuthorisationRequestDTO request){

        Authorisation authorisation = new Authorisation();
        BeanUtils.copyProperties(request,authorisation);
        authorisationRepository.save(authorisation);
    }

    @Override
    @Transactional(value = "transactionManager")
    public JpaRepository<Authorisation, String> getRepository(){
        return authorisationRepository;
    }

    @Override
    @Transactional(value = "transactionManager")
    public List<User> getUserByRole(String name) {
        return null;
    }

    @Transactional(value = "transactionManager")
    public void deleteAuthorisation(String id) {
        authorisationRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public AuthorisationResponseDTO getAuthorisationById(String id) {

        AuthorisationResponseDTO authorisationResponse = new AuthorisationResponseDTO();
        Authorisation authorisation= authorisationRepository.findAuthorisationById(id);
        BeanUtils.copyProperties(authorisation, authorisationResponse);

        return authorisationResponse;
    }

    @Transactional(value = "transactionManager")
    public List<AuthorisationResponseDTO> getAuthorisationByBranchId(String branchId){

        List<AuthorisationResponseDTO> response = new ArrayList<AuthorisationResponseDTO>();
        List<Authorisation> authorisationList = authorisationRepository.findAuthorisationByBranchId(branchId);

        for (Authorisation authorisation : authorisationList){

            AuthorisationResponseDTO authorisationResponseDTO = new AuthorisationResponseDTO();
            BeanUtils.copyProperties(authorisation, authorisationResponseDTO);

            response.add(authorisationResponseDTO);

        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public List<AuthorisationResponseDTO> getAuthorisationByUserId(String userId) {

        List<AuthorisationResponseDTO> responseDTO = new ArrayList<>();

        List<Authorisation> authorisation = authorisationRepository.findAllByUserId(userId);

        for (Authorisation authorisation1 : authorisation) {
            AuthorisationResponseDTO response = new AuthorisationResponseDTO();
            BeanUtils.copyProperties(authorisation1, response);

            responseDTO.add(response);

        }

        return responseDTO;
    }

    @Transactional(value = "transactionManager")
    public List<AuthorisationResponseDTO> getAuthorisationByBranchIdAndAuthLevel(String branchId, String authLevel){

        List<AuthorisationResponseDTO> response = new ArrayList<AuthorisationResponseDTO>();
        List<Authorisation> authorisationList= authorisationRepository.findAuthorisationByBranchIdAndAuthLevel(branchId, authLevel);

        for (Authorisation authorisation : authorisationList){

             AuthorisationResponseDTO authorisationResponseDTO = new AuthorisationResponseDTO();
            BeanUtils.copyProperties(authorisation, authorisationResponseDTO);

            response.add(authorisationResponseDTO);

        }

        return response;
    }

}




