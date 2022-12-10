package com.server.ecommerceapp.controller;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.exception.DuplicateUserException;
import com.server.ecommerceapp.dto.AuthRequestDTO;
import com.server.ecommerceapp.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/v1")
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping(value = "/register")
    public ResponseEntity<AppUserDTO> register(@RequestBody AuthRequestDTO authRequestDTO) throws DuplicateUserException {
        AppUserDTO appUserDTO = authService.registerUser(authRequestDTO);
        return ResponseEntity.ok(appUserDTO);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody AuthRequestDTO authRequestDTO) {
        String response = authService.loginUser(authRequestDTO);
        return ResponseEntity.ok(response);
    }
}
