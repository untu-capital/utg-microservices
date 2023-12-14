package com.untucapital.musoniservice.model;

import lombok.*;

import jakarta.persistence.*;

/**
 * @author tjchidanika
 * @created 5/10/2023
 */

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction_purposes")
@Getter
@Setter
public class TransactionPurpose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
}
