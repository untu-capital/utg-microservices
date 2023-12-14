package com.example.notification.dto.messaging;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EmailRequest {

    String recipientName;
    String recipientMessage;
    String senderName;
}
