package com.server.ecommerceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppUserDTO {

    private String username;

    private String email;

    private String password;

    private Collection<RoleDTO> roles;
}

