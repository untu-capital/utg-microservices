package com.example.creditms.dto.req;

import com.example.creditms.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class BalanceSheetRequestDTO extends AbstractEntityDTO {

    private String id;
    private String loanId;
    private String name;
    private String comment;
    private double amount;

}
