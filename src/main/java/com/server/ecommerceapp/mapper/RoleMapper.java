package com.server.ecommerceapp.mapper;

import com.server.ecommerceapp.dto.RoleDTO;
import com.server.ecommerceapp.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class RoleMapper {

    public Role toRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleName(roleDTO.getRoleName());
        return role;
    }

    public RoleDTO toRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleName(role.getRoleName());
        return roleDTO;
    }
}
