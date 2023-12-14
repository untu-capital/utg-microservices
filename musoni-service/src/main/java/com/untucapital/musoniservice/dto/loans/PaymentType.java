package com.untucapital.musoniservice.dto.loans;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PaymentType {

    private int id;
    private String name;
    private boolean deleted;
}
