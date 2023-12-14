package com.example.creditms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "currentAssest")
public class CurrentAsset extends AbstractEntity{
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @Column(name = "date_from")
    private String dateFrom;

    @Column(name = "date_to")
    private String dateTo;

    @Column(name = "value")
    private double value;
    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
