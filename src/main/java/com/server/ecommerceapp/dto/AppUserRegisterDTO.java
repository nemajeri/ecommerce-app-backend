package com.server.ecommerceapp.dto;

import lombok.*;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppUserRegisterDTO {

    private String username;

    private String email;

    private String password;

    private RoleDTO role;
}
