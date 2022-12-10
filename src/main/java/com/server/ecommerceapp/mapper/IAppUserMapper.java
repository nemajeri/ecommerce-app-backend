package com.server.ecommerceapp.mapper;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.model.AppUser;
import com.server.ecommerceapp.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.stream.Collectors;
import java.util.Collection;



@Mapper(componentModel = "spring", imports = {Collectors.class, Collection.class})
public interface IAppUserMapper {

    @Mapping(target = "username")
    @Mapping(target = "email")
    @Mapping(target = "password")
    @Mapping(target = "roles", expression = "java(appUser.getRoles().stream().map(this::getRoleName).collect(Collectors.toCollection(ArrayList::new)))")
    AppUserDTO toAppUserDTO(AppUser appUser);

    default String getRoleName(Role role) {
        return role.getRoleName();
    }
}