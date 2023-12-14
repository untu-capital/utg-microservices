package com.example.creditms.config;

import com.example.creditms.utils.FormatterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@Component
public class LoggingFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Initializing Logging Filter: {}", FormatterUtil.toJson(this));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        log.info("> Usuite UTG Request > - {}: {}, parameters={}", req.getMethod(), req.getRequestURI(), FormatterUtil.toJson(req.getParameterMap()));

        chain.doFilter(request, response);

        log.info("< Usuite UTG Response < - {}: {}", res.getStatus(), res.getHeaderNames().toArray());
    }

    @Override
    public void destroy() {
        log.warn("Destructing Logging Filter: {}", this);
    }
}
