package com.server.ecommerceapp.controller;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.dto.AppUserLoginDTO;
import com.server.ecommerceapp.dto.AppUserRegisterDTO;
import com.server.ecommerceapp.exception.DuplicateUserException;
import com.server.ecommerceapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController("Controller for user authentication")
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<AppUserDTO> register(@RequestBody AppUserRegisterDTO appUserRegisterDTO) throws DuplicateUserException {
        AppUserDTO appUserDTO = authService.registerUser(appUserRegisterDTO);
        return ResponseEntity.ok(appUserDTO);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody AppUserLoginDTO appUserLoginDTO) {
        String response = authService.loginUser(appUserLoginDTO);
        return ResponseEntity.ok(response);
    }
}
