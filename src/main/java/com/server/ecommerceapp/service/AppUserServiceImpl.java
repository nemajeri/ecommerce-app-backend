package com.server.ecommerceapp.service;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.exception.UserNotFoundException;
import com.server.ecommerceapp.mapper.IAppUserMapper;
import com.server.ecommerceapp.model.AppUser;
import com.server.ecommerceapp.model.Role;
import com.server.ecommerceapp.repository.IRoleRepository;
import com.server.ecommerceapp.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("User Service Implementation")
@RequiredArgsConstructor
public class AppUserServiceImpl implements IAppUserService {
    private final IUserRepository userRepository;

    private final IRoleRepository roleRepository;

    private final IAppUserMapper appUserMapper;

    @Override
    public void parseRoleToUser(String userName, String roleName) {
        AppUser appUser = userRepository.findByUsername(userName);
        Role role = roleRepository.findByRoleName(roleName);
        appUser.getRoles().add(role);
    }

    @Override
    public AppUserDTO getUser(String userName) throws UserNotFoundException {
        AppUser appUser = userRepository.getByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException(userName));

        AppUserDTO appUserDTO = appUserMapper.toAppUserDTO(appUser);

        return appUserDTO;
    }

    @Override
    public AppUserDTO updateUser(String username,
                                 String email,
                                 String password) {
        AppUser appUser = userRepository.findByUsername(username);

        if (username != null && username.length() > 0) {
            appUser.setUsername(username);
        }

        if (email != null && email.length() > 0) {
            appUser.setUsername(email);
        }

        if (password != null && password.length() > 0) {
            appUser.setUsername(password);
        }

        appUser = userRepository.save(appUser);
        return appUserMapper.toAppUserDTO(appUser);
    }
}
