package com.untucapital.musoniservice.model.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepaymentFrequencyType {
    private int id;
    private String code;
    private String value;
}
