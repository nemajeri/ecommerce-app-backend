package com.server.ecommerceapp.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "role", nullable = false)
    private String roleName;
    @Column(name = "app_users", nullable = false)
    @ManyToMany(mappedBy = "roles")
    private Set<AppUser> appUsers;

}
