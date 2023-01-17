package com.server.ecommerceapp.configuration;

import com.server.ecommerceapp.dto.AppUserDTO;
import com.server.ecommerceapp.dto.RoleDTO;
import com.server.ecommerceapp.mapper.AppUserMapper;
import com.server.ecommerceapp.model.AppUser;
import com.server.ecommerceapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class AppUserConfiguration {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AppUserMapper appUserMapper;

    @Bean
    CommandLineRunner populateUserAndRoleTables() {
        return args -> {
            saveAppUser(new AppUserDTO("John Doe", "john.doe@gmail.com", "12654", new RoleDTO("ROLE_SELLER")));
            saveAppUser(new AppUserDTO("John Boe", "john.boe@gmail.com", "12655", new RoleDTO("ROLE_BUYER")));
            saveAppUser(new AppUserDTO("Jane Soe", "jane.soe@gmail.com", "12656", new RoleDTO("ROLE_SELLER")));
            saveAppUser(new AppUserDTO("Steve Saw", "steve.saw@gmail.com", "12657", new RoleDTO("ROLE_BUYER")));
        };
    }

    private void saveAppUser(AppUserDTO appUserDTO) {
        AppUser appUser = appUserMapper.toAppUser(appUserDTO);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);
    }
}

