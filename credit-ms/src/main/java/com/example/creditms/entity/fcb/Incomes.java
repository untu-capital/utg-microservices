package com.example.creditms.entity.fcb;


import com.example.creditms.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "incomes")
public class Incomes extends AbstractEntity {

    @Column(name = "employer")
    private String employer;

    @Column(name = "industry")
    private String industry;

    @Column(name = "salary_range")
    private String salaryRange;

    @Column(name = "position")
    private String position;


    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
