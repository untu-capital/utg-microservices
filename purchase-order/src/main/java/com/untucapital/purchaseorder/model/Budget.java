package com.untucapital.purchaseorder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Budget extends AbstractEntity {

    @Column(name = "category")
    private String category;


    @Column(name = "year")
    private int year;

    @Column(name = "month")
    private int month;

    @Column(name = "amount")
    private Float amount;

}
