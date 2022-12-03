package com.server.ecommerceapp.repository;

import com.server.ecommerceapp.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
