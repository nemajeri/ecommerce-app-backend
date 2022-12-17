package com.server.ecommerceapp.mapper;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.dto.RoleDTO;
import com.server.ecommerceapp.model.AppUser;
import com.server.ecommerceapp.model.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class AppUserMapper {

    public AppUserDTO toAppUserDTO(AppUser appUser) {
        String username = appUser.getUsername();
        String email = appUser.getEmail();
        String password = appUser.getPassword();
        Collection<RoleDTO> roles = appUser.getRoles()
                .stream()
                .map(role -> new RoleDTO(null, role.getRoleName(), new HashSet<>()))
                .collect(Collectors.toList());

        return new AppUserDTO(username, email, password, roles);
    }


    public AppUser toAppUser(AppUserDTO appUserDTO) {
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserDTO.getUsername());
        appUser.setEmail(appUserDTO.getEmail());
        appUser.setPassword(appUserDTO.getPassword());
        appUser.setRoles(appUserDTO.getRoles().stream().map(role -> new Role(null, role.getRoleName(), new HashSet<>())).collect(Collectors.toCollection(ArrayList::new)));
        appUser.setUsername(appUserDTO.getUsername());

        return appUser;
    }

    public Role toRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleName(roleDTO.getRoleName());

        return role;
    }
}
