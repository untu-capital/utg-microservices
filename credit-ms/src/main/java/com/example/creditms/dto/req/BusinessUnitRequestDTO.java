package com.example.creditms.dto.req;

import com.example.creditms.entity.AbstractEntity;
import lombok.Data;

@Data
public class BusinessUnitRequestDTO extends AbstractEntity {

    private String loanId;
    private String name;
    private String address;

    public BusinessUnitRequestDTO() {
    }

    public BusinessUnitRequestDTO(String loanId, String name, String address) {
        this.loanId = loanId;
        this.name = name;
        this.address = address;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "BusinessUnit{" +
                "loanId='" + loanId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
