package com.untucapital.securityserver.dto.payload;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private String fileDescription;
    private String userId;
    private String loanId;
    private long size;

}
