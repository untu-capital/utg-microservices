package com.example.creditms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "bank")
public class Bank extends AbstractEntity {

    @NotNull
    @Column(nullable = false, name = "loan_id")
    private String loanId;

    @Column(name = "bank_name")
    private String bankName;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
