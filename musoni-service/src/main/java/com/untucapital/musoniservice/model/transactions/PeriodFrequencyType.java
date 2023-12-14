package com.untucapital.musoniservice.model.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PeriodFrequencyType {
    private int id;
    private String code;
    private String value;
}
