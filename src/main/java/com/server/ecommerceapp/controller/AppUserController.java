package com.server.ecommerceapp.controller;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.exception.UserNotFoundException;
import com.server.ecommerceapp.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController("Controller for manipulating the user")
@RequiredArgsConstructor
@RequestMapping(value ="/api/v1/user")
public class AppUserController {

    private final AppUserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<AppUserDTO> loadUser(@PathVariable("username") String username) throws UserNotFoundException {
        AppUserDTO appUserDTO = userService.getUser(username);
        return ResponseEntity.ok(appUserDTO);
    }
}

