package com.example.creditms.dto.res;


import com.example.creditms.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class BankResponseDTO extends AbstractEntityDTO {

    private String loanId;
    private String bankName;

}
