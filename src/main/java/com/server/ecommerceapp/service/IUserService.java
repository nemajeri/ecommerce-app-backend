package com.server.ecommerceapp.service;

import com.server.ecommerceapp.model.Role;
import com.server.ecommerceapp.model.AppUser;

public interface IUserService {
    AppUser saveUser(AppUser appUser);

    void parseRoleToUser(String email, String roleName);

    AppUser getUser(String email);

    Role saveRole(Role role);
}
