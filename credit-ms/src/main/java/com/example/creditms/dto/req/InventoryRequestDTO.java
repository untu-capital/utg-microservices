package com.example.creditms.dto.req;

import com.example.creditms.dto.AbstractEntityDTO;


public class InventoryRequestDTO extends AbstractEntityDTO {

    private String loanId;

    private String product;

    private String quantity;

    private String purchasePrice;

    private String salePrice;

    private String stockCountDate;


    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getStockCountDate() {
        return stockCountDate;
    }

    public void setStockCountDate(String stockCountDate) {
        this.stockCountDate = stockCountDate;
    }

}
