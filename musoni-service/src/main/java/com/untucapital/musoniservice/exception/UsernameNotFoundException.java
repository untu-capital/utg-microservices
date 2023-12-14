package com.untucapital.musoniservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsernameNotFoundException extends RuntimeException {

    private final String resourceName;

    public UsernameNotFoundException(String resourceName) {
        super(String.format("The user with this username: %s is not in the system", resourceName));
        this.resourceName = resourceName;

    }

    public String getResourceName() {
        return resourceName;
    }

}
