package com.example.creditms.dto.req;

import com.example.creditms.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class BranchesRequestDTO extends AbstractEntityDTO {

    private String branchName;
    private String branchAddress;
    private String branchTellPhone;
    private String branchStatus;
    private String vaultAccountNumber;
    private String branchCode;
    private String googleMap;
    private String directionsLink;
    private int code;

}
