package com.untucapital.purchaseorder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pos_balance_sheet")
public class POSBalanceSheet extends AbstractEntity {

    @Column(name = "month")
    private String month;

    @Column(name = "budget")
    private Float budget;

    @Column(name = "expenditure")
    private Float expenditure;

    @Column(name = "balance")
    private Float balance;

    @Column(name = "year")
    private int year;
}
