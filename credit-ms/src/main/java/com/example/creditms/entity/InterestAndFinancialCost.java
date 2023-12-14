package com.example.creditms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "interestAndFinancialCost")
public class InterestAndFinancialCost extends AbstractEntity{
    @NotNull
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "interest_and_financial_cost")
    private String interestAndFinancialCost;

    @Column(name = "taxes")
    private String taxes;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getInterestAndFinancialCost() {
        return interestAndFinancialCost;
    }

    public void setInterestAndFinancialCost(String interestAndFinancialCost) {
        this.interestAndFinancialCost = interestAndFinancialCost;
    }

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }
}
