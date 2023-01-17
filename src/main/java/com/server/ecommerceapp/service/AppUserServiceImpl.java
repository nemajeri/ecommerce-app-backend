package com.server.ecommerceapp.service;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.dto.LoadAppUserDTO;
import com.server.ecommerceapp.dto.RoleDTO;
import com.server.ecommerceapp.dto.UpdateAppUserDTO;
import com.server.ecommerceapp.exception.UserNotFoundException;
import com.server.ecommerceapp.mapper.AppUserMapper;
import com.server.ecommerceapp.mapper.RoleMapper;
import com.server.ecommerceapp.model.AppUser;
import com.server.ecommerceapp.model.Role;
import com.server.ecommerceapp.repository.RoleRepository;
import com.server.ecommerceapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("User Service Implementation")
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final UserRepository userRepository;

    private final AppUserMapper appUserMapper;

    private final PasswordEncoder passwordEncoder;


    @Override
    public LoadAppUserDTO getUser(String username) throws UserNotFoundException {
        AppUser appUser = userRepository.getByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username + "cannot be found"));

        LoadAppUserDTO loadAppUserDTO = appUserMapper.toLoadAppUserDTO(appUser);

        return loadAppUserDTO;
    }

    @Override
    public void updateUser(UpdateAppUserDTO updatedappUserDTO) {
        AppUser appUser = userRepository.findByUsername(updatedappUserDTO.getUsername());

            if (updatedappUserDTO.getEmail() != null && updatedappUserDTO.getEmail().length() > 0) {
                appUser.setEmail(updatedappUserDTO.getEmail());
            }

            if (updatedappUserDTO.getPassword() != null && updatedappUserDTO.getPassword().length() > 0) {
                appUser.setPassword(passwordEncoder.encode(updatedappUserDTO.getPassword()));
            }

            userRepository.save(appUser);
    }
}
