package com.untucapital.cms.dto.res;

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
public class TransactionPurposeResponseDTO {

    private Integer id;
    private String name;
}
