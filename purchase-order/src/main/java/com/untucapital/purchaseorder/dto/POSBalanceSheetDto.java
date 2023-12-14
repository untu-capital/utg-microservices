package com.untucapital.purchaseorder.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class POSBalanceSheetDto {
    private String month;
    private Float budget;
    private Float expenditure;
    private Float balance;
    private int year;
}
