package com.example.notification.dto.messaging;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EmailResponse {

    private String responseCode;
    private String responseMessage;
    private String responseDescription;
}
