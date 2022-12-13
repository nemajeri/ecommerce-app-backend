package com.server.ecommerceapp.service;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.exception.DuplicateUserException;
import com.server.ecommerceapp.dto.AuthRequestDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    AppUserDTO registerUser(AuthRequestDTO authRequestDTO) throws DuplicateUserException;

    String loginUser(AuthRequestDTO authRequestDTO);

    UserDetails loadUserByUsername(String userName);
}
