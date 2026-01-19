package com.example.SecurityApp.controller;

import com.example.SecurityApp.dto.LoginDto;
import com.example.SecurityApp.dto.SignUpDto;
import com.example.SecurityApp.dto.UserDto;
import com.example.SecurityApp.services.AuthService;
import com.example.SecurityApp.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    //signup api
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);

    }

    //for login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto , HttpServletRequest request , HttpServletResponse response) {
        String token = authService.login(loginDto);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);//it can not be accessed by only http by this
        response.addCookie(cookie);
        return ResponseEntity.ok(token);
    }
}
