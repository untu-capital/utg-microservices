package com.example.creditms.dto.res;


import com.example.creditms.dto.AbstractEntityDTO;

public class InventoryTwoMicroResponseDTO extends AbstractEntityDTO {

    private String loanId;

    private String name;

    private String marketValue;

    private double discount;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
