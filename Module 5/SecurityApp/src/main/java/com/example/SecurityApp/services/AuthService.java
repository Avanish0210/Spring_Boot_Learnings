package com.example.SecurityApp.services;

import com.example.SecurityApp.dto.LoginDto;
import com.example.SecurityApp.dto.LoginResponseDto;
import com.example.SecurityApp.entities.Session;
import com.example.SecurityApp.entities.User;
import com.example.SecurityApp.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final SessionService sessionService;
    private final SessionRepository sessionRepository;

    public LoginResponseDto login(LoginDto loginDto) {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String accessToken =  jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        sessionService.generateNewSession(user , refreshToken);

        return new LoginResponseDto(user.getId(),  accessToken, refreshToken);
    }

    public LoginResponseDto refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        sessionService.validateSession(refreshToken);
        User user = userService.getUserById(userId);


        String accessToken = jwtService.generateAccessToken(user);

        return new LoginResponseDto(user.getId(),  accessToken, refreshToken);
    }

    public void logout(String refreshToken) {
        Session session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()-> new AuthenticationServiceException("Invalid Token"));

        sessionRepository.delete(session);
    }
}
