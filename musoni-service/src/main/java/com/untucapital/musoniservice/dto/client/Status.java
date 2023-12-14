package com.untucapital.musoniservice.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Status {
    private int id;
    private String code;
    private String value;

}
