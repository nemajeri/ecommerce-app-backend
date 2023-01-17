package com.server.ecommerceapp.service;

import com.server.ecommerceapp.dto.AppUserLoginDTO;
import com.server.ecommerceapp.dto.AppUserRegisterDTO;
import com.server.ecommerceapp.exception.DuplicateUserException;
import com.server.ecommerceapp.mapper.AppUserMapper;
import com.server.ecommerceapp.model.AppUser;
import com.server.ecommerceapp.model.Role;
import com.server.ecommerceapp.repository.UserRepository;
import com.server.ecommerceapp.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("Authentication Service Implementation")
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final AppUserMapper appUserMapper;

    @Override
    public void registerUser(AppUserRegisterDTO appUserRegisterDTO) throws DuplicateUserException {
        Optional<AppUser> appUser = userRepository.getByUsername(appUserRegisterDTO.getUsername());

        if (appUser.isPresent()) {
            throw new DuplicateUserException();
        }

        AppUser createdAppUser = appUserMapper.toAppUser(appUserRegisterDTO);
        userRepository.save(createdAppUser);
    }

    @Override
    public String loginUser(AppUserLoginDTO appUserLoginDTO) {
        Optional<AppUser> appUser = userRepository.getByUsername(appUserLoginDTO.getUsername());
        if (!appUser.isPresent()) {
            throw new BadCredentialsException("Invalid username or password");
        }
        AppUser user = appUser.get();
        if (!passwordEncoder.matches(appUserLoginDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), mapRolesToAuthorities(Collections.singleton(user.getRole())));
        return jwtTokenProvider.generateToken(authentication);
    }


    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }
}
