package com.untucapital.purchaseorder.dto.req;


import com.untucapital.purchaseorder.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@RequiredArgsConstructor
@Data
public class BudgetRequestDTO extends AbstractEntityDTO {

    private String category;
    private int year;
    private int month;
    private Float amount;

}
