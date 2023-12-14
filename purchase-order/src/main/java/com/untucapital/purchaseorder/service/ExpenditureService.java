package com.untucapital.purchaseorder.service;



import com.untucapital.purchaseorder.dto.ExpenditureDto;
import com.untucapital.purchaseorder.dto.ExpenditureResponseDto;
import com.untucapital.purchaseorder.dto.POSBalanceSheetDto;
import com.untucapital.purchaseorder.model.Budget;
import com.untucapital.purchaseorder.model.Expenditure;
import com.untucapital.purchaseorder.processor.ExpenditureProcessor;
import com.untucapital.purchaseorder.repository.ExpenditureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenditureService {

    private final ExpenditureRepository expenditureRepository;
    private final BudgetService budgetService;
    private final ExpenditureProcessor expenditureProcessor;

    @Transactional(value = "transactionManager")
    public ExpenditureDto getExpenditureById(Integer id) {

        ExpenditureDto response = new ExpenditureDto();
        Optional<Expenditure> expenditure = expenditureRepository.findById(id);

        if (expenditure.isPresent()) {

            BeanUtils.copyProperties(expenditure.get(), response);

        } else {

            throw new RuntimeException("Expenditure not found");
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public List<ExpenditureResponseDto> getExpenditureByCategoryAndPeriod(String category, LocalDateTime dateFrom, LocalDateTime dateTor) {

        log.info("dateFrom: {}", dateFrom);
        List<Expenditure> expenditureList = expenditureRepository.findByCategoryAndCreatedAtBetween(category, dateFrom, dateTor);

        log.info("Expenditure List: {}", expenditureList);
        List<ExpenditureResponseDto> response = expenditureProcessor.setResponse(expenditureList);

        return response;
    }

    @Transactional(value = "transactionManager")
    public List<ExpenditureResponseDto> getTotalExpenditureByYearAndMonth(int year, String month) {


        List<Expenditure> expenditureList = expenditureRepository.findByMonthAndYear(month, year);

        List<ExpenditureResponseDto> response = expenditureProcessor.setResponse(expenditureList);

        return response;
    }

    @Transactional(value = "transactionManager")
    public List<ExpenditureResponseDto> getExpenditureByYear( int year) {



        List<Expenditure> expenditureList = expenditureRepository.findByYear( year);

        List<ExpenditureResponseDto> response = expenditureProcessor.setResponse(expenditureList);

        return response;
    }

//    @Transactional(value = "transactionManager")
//    public List<ExpenditureResponseDto> getExpenditureByMonthAndCategory( int year) {
//
//        List<Expenditure> expenditureList = expenditureRepository.findByYear( year);
//
//        List<ExpenditureResponseDto> response = expenditureProcessor.setResponse(expenditureList);
//
//        return response;
//    }

    @Transactional(value = "transactionManager")
    public List<ExpenditureResponseDto> getExpenditureByCategoryAndMonthAndYear(String category, String month,int year) {

        List<Expenditure> expenditureList = expenditureRepository.findByCategoryAndMonthAndYear(category,month, year);

        List<ExpenditureResponseDto> response = expenditureProcessor.setResponse(expenditureList);

        return response;
    }

    @Transactional(value = "transactionManager")
    public List<ExpenditureResponseDto> getExpenditureByPeriod(LocalDateTime dateFrom, LocalDateTime dateTo) {


        List<Expenditure> expenditureList = expenditureRepository.findByCreatedAtBetween(dateFrom, dateTo);

        List<ExpenditureResponseDto> response = expenditureProcessor.setResponse(expenditureList);

        return response;
    }

    @Transactional(value = "transactionManager")
    public List<Object[]> getExpenditureSumsByMonth(int year) {
        return expenditureRepository.sumExpenditureByMonth(year);
    }
    @Transactional(value = "transactionManager")
    public List<POSBalanceSheetDto> compareBudgetWithExpenditure(int year) {

        List<POSBalanceSheetDto> posBalanceSheetList = new ArrayList<>();

        List<Object[]> expenditureDtoList = getExpenditureSumsByMonth(year);
        List<Budget> budgetList = budgetService.getBudgetByYear(year);

        posBalanceSheetList = expenditureProcessor.setBalanceSheetResponse(budgetList, expenditureDtoList, year);

        return posBalanceSheetList;
    }

}
