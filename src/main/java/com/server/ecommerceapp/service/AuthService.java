package com.server.ecommerceapp.service;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.dto.AppUserLoginDTO;
import com.server.ecommerceapp.dto.AppUserRegisterDTO;
import com.server.ecommerceapp.exception.DuplicateUserException;

public interface AuthService {

    AppUserDTO registerUser(AppUserRegisterDTO appUserRegisterDTO) throws DuplicateUserException;

    String loginUser(AppUserLoginDTO appUserLoginDTO);

}
