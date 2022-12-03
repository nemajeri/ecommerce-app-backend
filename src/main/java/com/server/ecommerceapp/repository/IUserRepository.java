package com.server.ecommerceapp.repository;

import com.server.ecommerceapp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findByUsername(String name);

}
