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
public class CmsVaultRequestDTO extends AbstractEntityDTO {

    private String account;
    private String name;
    private String description;



}
