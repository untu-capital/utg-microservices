package com.untucapital.cms.dto.res;

import com.untucapital.cms.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CmsApproverResponseDTO extends AbstractEntityDTO {

    private String vault;
    private String initiator;
    private String first_approver;
    private String second_approver;


}
