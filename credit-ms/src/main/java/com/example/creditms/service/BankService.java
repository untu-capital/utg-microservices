package com.example.creditms.service;


import com.example.creditms.dto.req.BankRequestDTO;
import com.example.creditms.dto.res.BankResponseDTO;
import com.example.creditms.entity.Bank;
import com.example.creditms.repository.BankRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@jakarta.transaction.Transactional
public class BankService {

    @Autowired
    BankRepository bankRepository;

    @Transactional(value = "transactionManager")
    public void saveBank(BankRequestDTO request) {
        Bank bank = new Bank();
        BeanUtils.copyProperties(request, bank);
        bankRepository.save(bank);
    }

    public List<BankResponseDTO> getByLoanId(String loanId) {

        List<BankResponseDTO> response = new ArrayList<>();
        List<Bank> bankList = bankRepository.findByLoanId(loanId);

        for (Bank bank : bankList) {
            BankResponseDTO responseDTO = new BankResponseDTO();
            BeanUtils.copyProperties(bank, responseDTO);
            response.add(responseDTO);
        }

        return response;
    }

}
