package com.example.creditms.dto.req;


import com.example.creditms.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class BankRequestDTO extends AbstractEntityDTO {

    private String loanId;
    private String bankName;

}
