package com.server.ecommerceapp.controller;

import com.server.ecommerceapp.dto.AppUserLoginDTO;
import com.server.ecommerceapp.dto.AppUserRegisterDTO;
import com.server.ecommerceapp.exception.DuplicateUserException;
import com.server.ecommerceapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;


@RestController("Controller for user authentication")
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<Object> register(@RequestBody AppUserRegisterDTO userRegisterDTO) {
        try {
            authService.registerUser(userRegisterDTO);
            return new ResponseEntity<>(CREATED);
        } catch (DuplicateUserException e) {
            return new ResponseEntity<>(CONFLICT);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody AppUserLoginDTO appUserLoginDTO) {
        try {
            String token = authService.loginUser(appUserLoginDTO);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(UNAUTHORIZED);
        }
    }
}

