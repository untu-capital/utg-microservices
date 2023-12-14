package com.untucapital.cms.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.io.IOException;

/**
 * @author Chirinda Nyasha Dell 24/11/2021
 */

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
    private static final Logger log = LoggerFactory.getLogger(AuthEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         org.springframework.security.core.AuthenticationException authException)
            throws IOException, ServletException {
        log.error("Unauthorized Request - {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
