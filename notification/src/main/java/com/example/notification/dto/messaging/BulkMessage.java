package com.example.notification.dto.messaging;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class BulkMessage {

    private String batchNumber;
    private List<Map> messages;
}
