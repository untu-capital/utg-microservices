package com.untucapital.purchaseorder.service;


import com.untucapital.purchaseorder.dto.req.BudgetRequestDTO;
import com.untucapital.purchaseorder.dto.res.BudgetResponseDTO;
import com.untucapital.purchaseorder.model.Budget;
import com.untucapital.purchaseorder.repository.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@Service
@RequiredArgsConstructor
public class BudgetService {

    public final BudgetRepository budgetRepository;

    //1. create budget
    @Transactional(value = "transactionManager")
    public BudgetResponseDTO createBudget(BudgetRequestDTO request) {

        Budget budget = new Budget();
        BudgetResponseDTO response = new BudgetResponseDTO();
        BeanUtils.copyProperties(request, budget);
        Budget budget1 = budgetRepository.save(budget);
        BeanUtils.copyProperties(budget1, response);

        return response;
    }

    //2. get budget by id
    @Transactional(value = "transactionManager")
    public BudgetResponseDTO getBudgetById(String id) {

        BudgetResponseDTO response = new BudgetResponseDTO();
        Budget budget = budgetRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(budget, response);
        return response;
    }

    //3. get all budgets
    @Transactional(value = "transactionManager")
    public List<BudgetResponseDTO> getAllBudgets() {

        List<BudgetResponseDTO> response = new ArrayList<BudgetResponseDTO>();
        List<Budget> budgetList = budgetRepository.findAll();

        for (Budget budget : budgetList) {
            BudgetResponseDTO budgetResponse = new BudgetResponseDTO();
            BeanUtils.copyProperties(budget, budgetResponse);

            response.add(budgetResponse);
        }

        return response;
    }

    //4. update budget
    @Transactional(value = "transactionManager")
    public BudgetResponseDTO updateBudget(BudgetRequestDTO budget) {

        BudgetResponseDTO response = new BudgetResponseDTO();
        Budget existingBudget = budgetRepository.findById(budget.getId()).orElse(null);

        assert existingBudget != null;
        existingBudget.setCategory(budget.getCategory());
        existingBudget.setYear(budget.getYear());
        existingBudget.setMonth(budget.getMonth());

        budgetRepository.save(existingBudget);
        BeanUtils.copyProperties(existingBudget, response);

        return response;
    }

    @Transactional(value = "transactionManager")
    public List<Budget> getBudgetByYear(Integer year) {

        BudgetResponseDTO budgetResponse = new BudgetResponseDTO();
        List<Budget> budget = budgetRepository.findByYear(year);

        return budget;
    }

    //5. delete budget
    @Transactional(value = "transactionManager")
    public BudgetResponseDTO deleteBudget(String id) {

        BudgetResponseDTO budgetResponse = new BudgetResponseDTO();
        Budget budget = budgetRepository.findById(id).orElse(null);
        budgetRepository.deleteById(id);
        BeanUtils.copyProperties(budget, budgetResponse);
        return budgetResponse;
    }
}