package com.untucapital.purchaseorder.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class POSSupplierRequestDTO {

    private Integer id;

    private String name;

    private String address;

    private String phone;

    private String contactPerson;

    private String comment;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
