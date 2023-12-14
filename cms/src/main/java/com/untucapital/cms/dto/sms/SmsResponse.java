package com.untucapital.cms.dto.sms;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SmsResponse {

    private String responseCode;
    private String responseMessage;
    private String responseDescription;
}
