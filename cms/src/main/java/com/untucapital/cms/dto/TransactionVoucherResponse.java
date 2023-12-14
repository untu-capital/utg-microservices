package com.untucapital.cms.dto;


import com.untucapital.cms.enums.ApprovalStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author tjchidanika
 * @created 9/10/2023
 */

@Getter
@Setter
@Builder
public class TransactionVoucherResponse {

    private Integer id;

    private UserDTO initiator;
    private String applicationDate;
    private String applicationNo;

    private UserDTO firstApprover;
    private String firstApprovedAt;
    private ApprovalStatus firstApprovalStatus;
    private String  firstApprovalComment;

    private UserDTO secondApprover;
    private String secondApprovedAt;
    private ApprovalStatus secondApprovalStatus;
    private String  secondApprovalComment;

    private BigDecimal amount;
    private String amountInWords;
    private String withdrawalPurpose;
    private String currency;

    private Integer denomination100;
    private Integer denomination50;
    private Integer denomination20;
    private Integer denomination10;
    private Integer denomination5;
    private Integer denomination2;
    private Integer denomination1;
    private Integer denominationCents;

    private BranchDTO branch;
    private VaultDTO fromVault;
    private VaultDTO toVault;

}
