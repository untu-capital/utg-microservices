package com.untucapital.musoniservice.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubStatus {

    private boolean active;
    private boolean isDefault;
}
