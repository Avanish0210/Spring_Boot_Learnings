package com.example.SecurityApp.Filters;

import com.example.SecurityApp.services.AuthService;
import com.example.SecurityApp.services.JwtService;
import com.example.SecurityApp.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j

public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        log.info("Request :"+ "Started time "+startTime + request.getMethod() +request.getRequestURI());
        filterChain.doFilter(request, response);
        long duration = System.currentTimeMillis() - startTime;
        log.info("Request :" + request.getMethod() +request.getRequestURI() + duration);
    }
}
