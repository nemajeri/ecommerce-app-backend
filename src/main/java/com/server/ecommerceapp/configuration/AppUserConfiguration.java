//package com.server.ecommerceapp.configuration;
//
//import com.server.ecommerceapp.dto.AppUserDTO;
//import com.server.ecommerceapp.dto.RoleDTO;
//import com.server.ecommerceapp.service.AppUserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//
//@Configuration("Configuration to populate user, roles and user with roles tables in DB")
//@RequiredArgsConstructor
//public class AppUserConfiguration {
//
//    @Bean
//    CommandLineRunner populateUserAndRoleTables(AppUserService userService) {
//        return args -> {
//            userService.saveRole(new RoleDTO(null, "ROLE_SELLER",new HashSet<>(new ArrayList<>())));
//            userService.saveRole(new RoleDTO(null,"ROLE_BUYER", new HashSet<>(new ArrayList<>())));
//
//            userService.saveAppUser(new AppUserDTO("John Doe","john.doe@gmail.com", "12654", new ArrayList<>()));
//            userService.saveAppUser(new AppUserDTO("John Boe","john.boe@gmail.com", "12655", new ArrayList<>()));
//            userService.saveAppUser(new AppUserDTO("Jane Soe","jane.soe@gmail.com", "12656", new ArrayList<>()));
//            userService.saveAppUser(new AppUserDTO("Steve Saw","steve.saw@gmail.com", "12657", new ArrayList<>()));
//
//            userService.parseRoleToUser("John Doe", "ROLE_BUYER");
//            userService.parseRoleToUser("John Boe", "ROLE_SELLER");
//            userService.parseRoleToUser("Jane Soe", "ROLE_SELLER");
//            userService.parseRoleToUser("Steve Saw", "ROLE_BUYER");
//        };
//    }
//}
