package com.example.creditms.service;

import com.example.creditms.dto.req.BankToRequestDTO;
import com.example.creditms.dto.res.BankToResponseDTO;
import com.example.creditms.entity.BankTo;
import com.example.creditms.repository.BankToRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@jakarta.transaction.Transactional
@RequiredArgsConstructor
public class BankToService {

    @Autowired
    private final BankToRepository bankToRepository;


    @Transactional(value = "transactionManager")
    public List<BankTo> listAllBankTo() {
        return bankToRepository.findAll();
    }

    @Transactional(value = "transactionManager")
    public void saveBankTo(BankToRequestDTO request) {
        BankTo bankTo = new BankTo();
        BeanUtils.copyProperties(request, bankTo);
        bankToRepository.save(bankTo);
    }

    //Get List of Business Units by Id
    @Transactional(value = "transactionManager")
    public List<BankToResponseDTO> listBankToByLoanId(String id, String bank){
        List<BankToResponseDTO> responseDTOList = new ArrayList<>();

        List<BankTo> bankToList = bankToRepository.findBankToByLoanIdAndBankOrderByMonthAsc(id, bank);

        for(BankTo bankTo : bankToList){
            BankToResponseDTO responseDTO = new BankToResponseDTO();
            BeanUtils.copyProperties(bankTo, responseDTO);

            responseDTOList.add(responseDTO);
        }

        return responseDTOList;
    }

    @Transactional(value = "transactionManager")
    public void deleteById(String id) {
        bankToRepository.deleteById(id);
    }
}
