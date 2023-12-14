package com.untucapital.musoniservice.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Gender {

    private int id;
    private String name;
    private boolean active;
    private boolean isDefault;
}
