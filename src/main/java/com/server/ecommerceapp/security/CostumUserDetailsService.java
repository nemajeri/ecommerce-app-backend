package com.server.ecommerceapp.security;


import com.server.ecommerceapp.model.AppUser;
import com.server.ecommerceapp.model.Role;
import com.server.ecommerceapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("Overriding default user details interface")
public class CostumUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.getByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with username : " + username + "not found!"));
        System.out.println("User details: " + appUser);
        System.out.println("UserDetails: " + new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), mapRolesToAuthorities(Collections.singleton(appUser.getRole()))));
        return new org.springframework.security.core.userdetails.User(
                appUser.getUsername(), appUser.getPassword(), mapRolesToAuthorities(Collections.singleton(appUser.getRole())));

    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }
}

