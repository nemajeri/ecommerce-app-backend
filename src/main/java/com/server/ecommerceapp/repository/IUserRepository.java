package com.server.ecommerceapp.repository;

import com.server.ecommerceapp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String name);

}
