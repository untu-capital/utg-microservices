package com.untucapital.purchaseorder.dto.res;

import com.untucapital.purchaseorder.dto.AbstractEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequisitionsResponseDTO extends AbstractEntityDTO {


    private String poNumber;

    private String poName;

    private String poTotal;

    private String poCount;

    private String poStatus;

    private String notes;

    private String userId;

    private Set<String> approvers;

    private List<String> attachments;


}
