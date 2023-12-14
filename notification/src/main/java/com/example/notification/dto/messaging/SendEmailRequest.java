package com.example.notification.dto.messaging;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SendEmailRequest {

    String recipientEmail;
    String recipientSubject;
    String emailText;
}
