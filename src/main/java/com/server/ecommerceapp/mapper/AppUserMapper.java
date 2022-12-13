package com.server.ecommerceapp.mapper;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.dto.RoleDTO;
import com.server.ecommerceapp.model.AppUser;
import com.server.ecommerceapp.model.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class AppUserMapper {

    public AppUserDTO toAppUserDTO(AppUser appUser) {
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setUsername(appUser.getUsername());
        appUserDTO.setEmail(appUser.getEmail());
        appUserDTO.setPassword(appUser.getPassword());
        appUserDTO.setRoles(appUser.getRoles().stream().map(this::getRoleName).collect(Collectors.toCollection(ArrayList::new)));
        return appUserDTO;
    }

    private String getRoleName(Role role) {
        return role.getRoleName();
    }

    public AppUser toAppUser(AppUserDTO appUserDTO) {
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserDTO.getUsername());
        appUser.setEmail(appUserDTO.getEmail());
        appUser.setPassword(appUserDTO.getPassword());
        appUser.setRoles(appUserDTO.getRoles().stream().map(this::getRole).collect(Collectors.toCollection(ArrayList::new)));
        appUser.setUsername(appUserDTO.getUsername());

        return appUser;
    }
    private Role getRole (String roleName) {
        return new Role(null , roleName);
    }

    public Role toRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleName(roleDTO.getRoleName());

        return role;
    }
}
