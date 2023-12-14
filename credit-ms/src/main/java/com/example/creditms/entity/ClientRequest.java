package com.example.creditms.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ClientRequestMicro")
public class ClientRequest extends AbstractEntity{

    @Column(name = "loan_id")
    private String loanId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "maturity")
    private String maturity;

    @Column(name = "instalments")
    private double instalments;

    @Column(name = "interest_rate")
    private double interestRate;


    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    public double getInstalments() {
        return instalments;
    }

    public void setInstalments(double instalments) {
        this.instalments = instalments;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
