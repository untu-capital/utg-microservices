package com.example.notification.dto.messaging;

import lombok.Data;

import java.util.List;

@Data
public class BulkSMSDTO {
    private List<String> phoneNumbers;
    private String message;
}
