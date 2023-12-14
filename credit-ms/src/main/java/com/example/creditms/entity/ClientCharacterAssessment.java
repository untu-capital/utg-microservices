package com.example.creditms.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ClientCharacterAssessmentMicro")
public class ClientCharacterAssessment extends AbstractEntity {
    @Column(name = "loan_id")
    private String loanId;

    @Column(name = "description")
    private String description;

    public ClientCharacterAssessment() {
    }

    public ClientCharacterAssessment(String loanId, String description) {
        this.loanId = loanId;
        this.description = description;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ClientCharacterAssessmentService{" +
                "loanId='" + loanId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
