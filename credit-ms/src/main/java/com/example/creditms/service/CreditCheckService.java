package com.example.creditms.service;

import com.example.creditms.entity.fcb.Response;
import com.example.creditms.entity.ClientLoan;
import com.example.creditms.exception.UntuSuiteException;
import com.example.creditms.repository.ClientRepository;
import com.example.creditms.repository.FCBResponseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCheckService {

    private static final Logger log = LoggerFactory.getLogger(CreditCheckService.class);

    private final FCBIntegrationService fcbIntegrationService;
    private final ClientRepository clientRepository;
    private final FCBResponseRepository fcbResponseRepository;

    @Autowired
    public CreditCheckService(FCBIntegrationService fcbIntegrationService, ClientRepository clientRepository, FCBResponseRepository fcbResponseRepository) {
        this.fcbIntegrationService = fcbIntegrationService;
        this.clientRepository = clientRepository;
        this.fcbResponseRepository = fcbResponseRepository;
    }

    @Transactional(value = "transactionManager")
    public ClientLoan fetchFCBCreditStatus(ClientLoan clientLoan) {
        log.info("Fetching FCB Credit Status for Client: {}, ID:{}", clientLoan.getFirstName() + clientLoan.getLastName(), clientLoan.getIdNumber());

        //clientLoan.setLoanStatus("PENDING");

        final Response creditResponse = fcbIntegrationService.performSearch(clientLoan)
                .orElseThrow(() -> new UntuSuiteException("Credit check failed for the Loan Application"));

        Response savedResponse = fcbResponseRepository.save(creditResponse);

        String creditStatus = "UNKNOWN";
        Integer creditScore = 0;
        if (creditResponse.getReport() == null){

        } else {
            creditStatus = creditResponse.getReport().get(0).getStatus();
            creditScore = creditResponse.getReport().get(0).getScore();
        }

        clientLoan.setFcbResponse(savedResponse);
        clientLoan.setFcbStatus(creditStatus);
        clientLoan.setFcbScore(creditScore);

        //if (creditStatus.equals("GREEN") || creditStatus.equals("GOOD")) {
        //  clientLoan.setLoanStatus("ACCEPTED");
        //} else if (creditStatus.equals("FAIR") || creditStatus.equals("ADVERSE") || creditStatus.equals("PEP")) {
        //clientLoan.setLoanStatus("REJECTED");
        // }
        return clientLoan;
    }

    //    @Scheduled(initialDelayString = "${fixed-delay.ms}", fixedDelayString = "${fixed-delay.ms}")
    @Transactional(value = "transactionManager")
    public void pollCreditChecks() {
        log.debug("Polling Inconclusive Credit Checks");
        long startTime = System.currentTimeMillis();

        final List<ClientLoan> clientLoans = clientRepository.findAll();

        List<ClientLoan> pendingClientCreditChecks = clientLoans
                .stream()
                .filter(cl -> "PENDING".equals(cl.getLoanStatus()))
                .collect(Collectors.toList());

        pendingClientCreditChecks.forEach(this::fetchFCBCreditStatus);

        long finishTime = System.currentTimeMillis();
        if (!pendingClientCreditChecks.isEmpty())
            log.debug("Completed Poll of Inconclusive Checks: {}. Poll took {}ms", LocalDateTime.now(), finishTime - startTime);
    }
}
