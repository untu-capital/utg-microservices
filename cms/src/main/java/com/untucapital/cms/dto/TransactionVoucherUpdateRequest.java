package com.untucapital.cms.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author tjchidanika
 * @created 4/10/2023
 */

@Setter
@Getter

public class TransactionVoucherUpdateRequest {
    private Integer id;

    private BigDecimal amount;
    private Integer fromVault;
    private Integer toVault;
    private String amountInWords;
    private Integer withdrawalPurpose;
    private String currency;

    private Integer denomination100;
    private Integer denomination50;
    private Integer denomination20;
    private Integer denomination10;
    private Integer denomination5;
    private Integer denomination2;
    private Integer denomination1;

    private Integer denominationCents;
}
