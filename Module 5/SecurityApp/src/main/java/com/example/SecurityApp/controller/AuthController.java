package com.example.SecurityApp.controller;

import com.example.SecurityApp.dto.LoginDto;
import com.example.SecurityApp.dto.LoginResponseDto;
import com.example.SecurityApp.dto.SignUpDto;
import com.example.SecurityApp.dto.UserDto;
import com.example.SecurityApp.entities.SessionEntity;
import com.example.SecurityApp.repositories.SessionRepository;
import com.example.SecurityApp.services.AuthService;
import com.example.SecurityApp.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final SessionRepository sessionRepository;

    @Value("${deploy.env}")
    private String deployEnv;
    //signup api
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);

    }

    //for login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto , HttpServletRequest request , HttpServletResponse response) {
        LoginResponseDto loginResponseDto = authService.login(loginDto);

        String username = loginDto.getEmail();

        sessionRepository.deleteByUserId(username);
        SessionEntity sessions = new SessionEntity();
        sessions.setToken(loginResponseDto.getAccessToken());
        sessions.setUserId(username);
        sessionRepository.save(sessions);



        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);//it can not be accessed by only http by this
        cookie.setSecure("production".equals(deployEnv));
        response.addCookie(cookie);
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request) {
        String refreshToken =  Arrays.stream(request.getCookies()).
                filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Invalid refreshToken"));

        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(loginResponseDto);

    }
}
