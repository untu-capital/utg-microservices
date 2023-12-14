package com.example.creditms.service;

import com.example.creditms.dto.req.DisbursementTicketRequestDTO;
import com.example.creditms.entity.DisbursementTicket;
import com.example.creditms.entity.User;
import com.example.creditms.repository.DisbursementTicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@jakarta.transaction.Transactional
@Service
public class DisbursementTicketService extends AbstractService<DisbursementTicket> {

    private static final Logger log = LoggerFactory.getLogger(DisbursementTicketService.class);

    private final DisbursementTicketRepository disbursementTicketRepository;

    public DisbursementTicketService(DisbursementTicketRepository disbursementTicketRepository) {
        this.disbursementTicketRepository = disbursementTicketRepository;
    }

    @Transactional(value = "transactionManager")
    public void saveDisbursementTicket(DisbursementTicketRequestDTO requestDTO) {

        DisbursementTicket disbursementTicket = new DisbursementTicket();
        BeanUtils.copyProperties(requestDTO, disbursementTicket);
        disbursementTicketRepository.save(disbursementTicket);
    }
    @Override
    public List<User> getUserByRole(String name) {
        return null;
    }

    @Override
    protected JpaRepository<DisbursementTicket, String> getRepository() {
        return disbursementTicketRepository;
    }

    @Transactional(value = "transactionManager")
        public void deleteDisbursementTicket(String id) {
            disbursementTicketRepository.deleteById(id);
    }


    }




