package com.untucapital.cms.dto.req;

import com.untucapital.cms.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author panashe rutimhu
 * @created 6/11/2023
 */

@Data
@RequiredArgsConstructor
public class CmsApproverRequestDTO extends AbstractEntityDTO {

    private String vault;
    private String initiator;
    private String first_approver;

    private String second_approver;


}
