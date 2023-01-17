package com.server.ecommerceapp.service;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.dto.LoadAppUserDTO;
import com.server.ecommerceapp.dto.RoleDTO;
import com.server.ecommerceapp.dto.UpdateAppUserDTO;
import com.server.ecommerceapp.exception.UserNotFoundException;

public interface AppUserService {

    LoadAppUserDTO getUser(String username) throws UserNotFoundException;

    void updateUser(UpdateAppUserDTO updatedAppUserDTO);
}
