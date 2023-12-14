package com.untucapital.cms.dto.messaging;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ConfirmationEmailRequest {

    String firstName;
    String username;
    String token;
}
