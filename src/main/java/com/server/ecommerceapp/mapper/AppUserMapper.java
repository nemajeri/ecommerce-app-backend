package com.server.ecommerceapp.mapper;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.dto.AppUserRegisterDTO;
import com.server.ecommerceapp.dto.LoadAppUserDTO;
import com.server.ecommerceapp.dto.RoleDTO;
import com.server.ecommerceapp.model.AppUser;
import com.server.ecommerceapp.model.Role;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class AppUserMapper {
    @Autowired
    private RoleMapper roleMapper;

    private PasswordEncoder passwordEncoder;

    public LoadAppUserDTO toLoadAppUserDTO(AppUser appUser) {
        String username = appUser.getUsername();
        String email = appUser.getEmail();
        RoleDTO roleDTO = roleMapper.toRoleDTO(appUser.getRole());

        return new LoadAppUserDTO(username, email, roleDTO);
    }

        public AppUserDTO toAppUserDTO(AppUser appUser) {
            String username = appUser.getUsername();
            String email = appUser.getEmail();
            String password = appUser.getPassword();
            RoleDTO roleDTO = roleMapper.toRoleDTO(appUser.getRole());

        return new AppUserDTO(username, email, password, roleDTO);
    }

    public AppUser toAppUser(AppUserDTO appUserDTO) {
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserDTO.getUsername());
        appUser.setEmail(appUserDTO.getEmail());
        appUser.setPassword(appUserDTO.getPassword());
        Role role = roleMapper.toRole(appUserDTO.getRole());
        appUser.setRole(role);

        return appUser;
    }

    public AppUser toAppUser(AppUserRegisterDTO appUserRegisterDTO) {
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserRegisterDTO.getUsername());
        appUser.setEmail(appUserRegisterDTO.getEmail());
        appUser.setPassword(passwordEncoder.encode(appUserRegisterDTO.getPassword()));
        Role role = roleMapper.toRole(appUserRegisterDTO.getRole());
        appUser.setRole(role);

        return appUser;
    }
}
