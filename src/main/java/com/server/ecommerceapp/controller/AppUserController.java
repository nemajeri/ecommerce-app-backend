package com.server.ecommerceapp.controller;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.dto.LoadAppUserDTO;
import com.server.ecommerceapp.dto.UpdateAppUserDTO;
import com.server.ecommerceapp.exception.UserNotFoundException;
import com.server.ecommerceapp.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController("Controller for manipulating the user")
@RequiredArgsConstructor
@RequestMapping(value ="/api/v1/user")
public class AppUserController {

    private final AppUserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<LoadAppUserDTO> loadUser(@PathVariable("username") String username) {
        try {
            LoadAppUserDTO loadAppUserDTO = userService.getUser(username);
            return ResponseEntity.ok(loadAppUserDTO);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/update-user")
    public ResponseEntity<Object> updateAppUser(@RequestBody UpdateAppUserDTO updatedAppUserDTO) {
        try {
            userService.updateUser(updatedAppUserDTO);
            return new ResponseEntity<>(OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }
}

