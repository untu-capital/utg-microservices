package com.untucapital.musoniservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRole {

    private int id;
    private String name;
    private String description;
    private boolean disabled;
    private boolean systemDefined;
}
