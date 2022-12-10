package com.server.ecommerceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequestDTO {

    private String username;

    private String email;

    private String password;
}
