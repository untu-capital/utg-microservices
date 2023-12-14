package com.untucapital.purchaseorder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author tjchidanika
 * @created 7/9/2023
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
