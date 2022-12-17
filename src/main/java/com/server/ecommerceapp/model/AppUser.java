package com.server.ecommerceapp.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String password;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "app_user_roles",
                joinColumns = @JoinColumn(name = "app_user_id"),
                inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Collection<Role> roles;
}
