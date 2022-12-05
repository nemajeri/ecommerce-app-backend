package com.server.ecommerceapp.repository;

import com.server.ecommerceapp.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);

}
