package com.server.ecommerceapp.configuration;

import com.server.ecommerceapp.service.IUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RolesConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(IUserService userService) {
        return args -> {
//            userService.saveRole(new Role(null,"ROLE_SELLER"));
//            userService.saveRole(new Role(null,"ROLE_BUYER"));
//
//            userService.saveUser(new AppUser(null,"John Doe","john.doe@gmail.com", "12654", new ArrayList<>()));
//            userService.saveUser(new AppUser(null,"John Boe","john.boe@gmail.com", "12655", new ArrayList<>()));
//            userService.saveUser(new AppUser(null,"Jane Soe","jane.soe@gmail.com", "12656", new ArrayList<>()));
//            userService.saveUser(new AppUser(null,"Steve Saw","steve.saw@gmail.com", "12657", new ArrayList<>()));
//
//            userService.parseRoleToUser("john.doe@gmail.com", "ROLE_BUYER");
//            userService.parseRoleToUser("john.boe@gmail.com", "ROLE_SELLER");
//            userService.parseRoleToUser("jane.soe@gmail.com", "ROLE_SELLER");
//            userService.parseRoleToUser("steve.saw@gmail.com", "ROLE_BUYER");
        };
    }
}
