package com.untucapital.cms.dto.res.response;

public class SignatureResponse {

  private final String fileName;

  public SignatureResponse(String fileName) {
    this.fileName = fileName;
  }

  public String getFileName() {
    return fileName;
  }

}
