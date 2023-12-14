package com.untucapital.musoniservice.model.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentType {
    private int id;
    private String name;
    private boolean deleted;
}
