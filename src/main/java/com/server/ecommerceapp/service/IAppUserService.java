package com.server.ecommerceapp.service;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.exception.UserNotFoundException;

public interface IAppUserService {

    void parseRoleToUser(String email, String roleName);

    AppUserDTO getUser(String username) throws UserNotFoundException;

    AppUserDTO updateUser(
            String username,
            String email,
            String password
    );
}
