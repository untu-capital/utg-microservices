package com.example.creditms.dto.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

public class SignUpRequest {

    @NotBlank
    @Size(min = 2, max = 30)
    private String username;

    @NotBlank
    @Size(min = 2, max = 40)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 40)
    private String lastName;

    @NotBlank
    private String password;


    @Size(max = 40)
    @Email
    private String email;

    @NotNull
    private Long mobileNumber;


    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }
}
