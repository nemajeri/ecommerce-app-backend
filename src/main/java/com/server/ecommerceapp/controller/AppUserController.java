package com.server.ecommerceapp.controller;

import com.server.ecommerceapp.model.AppUser;
import com.server.ecommerceapp.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class AppUserController {
    private final IUserService userService;

    @PostMapping("/save")
    public ResponseEntity<AppUser> saveAppUser(@RequestBody AppUser appUser) {
        AppUser userToSave = userService.saveUser(appUser);
        return ResponseEntity.ok(userToSave);
    }
}

