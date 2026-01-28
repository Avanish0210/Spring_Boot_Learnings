package com.example.SecurityApp.Filters;

import com.example.SecurityApp.entities.SessionEntity;
import com.example.SecurityApp.repositories.SessionRepository;
import com.example.SecurityApp.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SessionValidationFilter extends OncePerRequestFilter {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Skip session validation for public endpoints
        String requestPath = request.getRequestURI();
        if (requestPath.startsWith("/auth/") || requestPath.equals("/error") || requestPath.equals("/posts")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            // No token or invalid format, continue to next filter
            filterChain.doFilter(request, response);
            return;
        }

        String token = requestTokenHeader.split("Bearer ")[1];
        String userId = jwtService.getUserIdFromToken(token).toString();

        Optional<SessionEntity> sessionEntity = sessionRepository.findByUserId(userId);
        if (sessionEntity.isPresent() && sessionEntity.get().getToken().equals(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Invalid/expired session
        response.setStatus(401);
        response.getWriter().write("Invalid session");
    }
}
