package com.untucapital.cms.dto.req;

import lombok.*;

/**
 * @author panashe rutimhu
 * @created 6/11/2023
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionPurposeRequestDTO {


    private Integer id;
    private String name;
}
