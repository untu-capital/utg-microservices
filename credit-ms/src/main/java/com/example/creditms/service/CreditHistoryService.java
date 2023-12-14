package com.example.creditms.service;

import com.example.creditms.dto.req.CreditHistoryRequestDTO;
import com.example.creditms.dto.res.CreditHistoryResponseDTO;
import com.example.creditms.entity.CreditHistory;
import com.example.creditms.repository.CreditHistoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CreditHistoryService {

    @Autowired
    CreditHistoryRepository creditHistoryRepository;

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void saveCreditHistory(CreditHistoryRequestDTO request){

        CreditHistory creditHistory = new CreditHistory();
        BeanUtils.copyProperties(request, creditHistory);
        creditHistoryRepository.save(creditHistory);
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<CreditHistoryResponseDTO> getCreditHistoryByLoanId(String loanId){

        List<CreditHistoryResponseDTO> response = new ArrayList<CreditHistoryResponseDTO>();
        List<CreditHistory> creditHistoryList = creditHistoryRepository.findByLoanId(loanId);

        for(CreditHistory creditHistory: creditHistoryList){
            CreditHistoryResponseDTO responseDTO = new CreditHistoryResponseDTO();
            BeanUtils .copyProperties(creditHistory, responseDTO);

            response.add(responseDTO);
        }
        return response;
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void deleteCreditHistory(String id){
        creditHistoryRepository.deleteById(id);
    }

}
