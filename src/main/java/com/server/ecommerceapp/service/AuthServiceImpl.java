package com.server.ecommerceapp.service;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.dto.AppUserLoginDTO;
import com.server.ecommerceapp.dto.AppUserRegisterDTO;
import com.server.ecommerceapp.exception.DuplicateUserException;
import com.server.ecommerceapp.mapper.AppUserMapper;
import com.server.ecommerceapp.model.AppUser;
import com.server.ecommerceapp.repository.UserRepository;
import com.server.ecommerceapp.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service("Authentication Service Implementation")
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final AppUserMapper appUserMapper;

    @Override
    public AppUserDTO registerUser(AppUserRegisterDTO appUserRegisterDTO) throws DuplicateUserException {
        Optional<AppUser> appUser = userRepository.getByUsername(appUserRegisterDTO.getUsername());

        if (appUser.isPresent()) {
            throw new DuplicateUserException();
        }

        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setUsername(appUserRegisterDTO.getUsername());
        appUserDTO.setEmail(appUserRegisterDTO.getEmail());
        appUserDTO.setPassword(passwordEncoder.encode(appUserRegisterDTO.getPassword()));

        AppUser createdAppUser = appUserMapper.toAppUser(appUserDTO);
        createdAppUser = userRepository.save(createdAppUser);

        return appUserDTO;
    }

    @Override
    public String loginUser(AppUserLoginDTO appUserLoginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        appUserLoginDTO.getUsername(),
                        appUserLoginDTO.getPassword()
                )
        );
        return jwtTokenProvider.generateToken(authentication);
    }
}
