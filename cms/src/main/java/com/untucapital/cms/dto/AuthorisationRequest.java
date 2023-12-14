package com.untucapital.cms.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthorisationRequest {

    private String branchId;

    private String authLevel;
}
