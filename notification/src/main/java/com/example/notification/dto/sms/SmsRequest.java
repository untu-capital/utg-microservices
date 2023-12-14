package com.example.notification.dto.sms;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SmsRequest {

    String phoneNumber;
    String message;
}
