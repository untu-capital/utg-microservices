package com.untucapital.securityserver.dto.messaging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class Bulk {
    private String batchNumber;
    private List<BulkSMS> messages;
}
