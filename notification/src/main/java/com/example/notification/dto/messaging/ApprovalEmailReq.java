package com.example.notification.dto.messaging;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApprovalEmailReq {

    String recipient;
    String message;
    String senderName;
}
