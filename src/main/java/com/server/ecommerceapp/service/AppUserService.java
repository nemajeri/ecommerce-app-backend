package com.server.ecommerceapp.service;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.dto.RoleDTO;
import com.server.ecommerceapp.exception.UserNotFoundException;

public interface AppUserService {

    void parseRoleToUser(String username, String roleName);

    AppUserDTO getUser(String username) throws UserNotFoundException;

    void saveRole(RoleDTO roleDTO);

    void saveAppUser(AppUserDTO appUserDTO);

    AppUserDTO updateUser(
            String username,
            String email,
            String password
    );
}
