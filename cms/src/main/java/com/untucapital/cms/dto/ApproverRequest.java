package com.untucapital.cms.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */

@Getter
@Setter
public class ApproverRequest {

    private Integer id;
    private String approvalStatus;
    private  String comment;

}
