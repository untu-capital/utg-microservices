package com.example.creditms.dto.req;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author tjchidanika
 * @created 7/9/2023
 */

@RequiredArgsConstructor
@Data
public class DepartmentRequestDTO {

    private Integer id;
    private String name;
}
