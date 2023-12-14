package com.untucapital.cms.dto.res;

import com.untucapital.cms.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author panashe rutimhu
 * @created 6/11/2023
 */

@Data
@RequiredArgsConstructor
public class CmsVaultResponseDTO extends AbstractEntityDTO {

    private String account;
    private String name;
    private String description;

}
