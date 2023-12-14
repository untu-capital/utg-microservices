package com.untucapital.cms.dto.payload;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

public class LoginReq {

    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
