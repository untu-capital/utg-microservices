package com.untucapital.cms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tjchidanika
 * @created 9/10/2023
 */

@Getter
@Setter
@Builder
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
}
