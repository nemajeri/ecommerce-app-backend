package com.server.ecommerceapp.service;

import com.server.ecommerceapp.model.Role;
import com.server.ecommerceapp.model.AppUser;
import com.server.ecommerceapp.repository.IRoleRepository;
import com.server.ecommerceapp.repository.IUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    public UserServiceImpl(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(name);

        if(user == null) {
            System.out.println("User doesnt exist!");
            throw new UsernameNotFoundException("User doesnt exist!");
        }
        System.out.println("User found!");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(user.getName(), user.getPassword(), authorities);
    }

    @Override
    public AppUser saveUser(AppUser appUser) {
        return userRepository.save(appUser);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void parseRoleToUser(String name, String roleName) {
        AppUser appUser = userRepository.findByUsername(name);
        Role role = roleRepository.findByName(roleName);
        appUser.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String name) {
        return userRepository.findByUsername(name);
    }

}
