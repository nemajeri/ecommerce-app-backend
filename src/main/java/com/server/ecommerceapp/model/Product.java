package com.server.ecommerceapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "rating", nullable = false)
    private Integer rating;
    @Column(name = "image", nullable = false)
    private String image;
    @Column(name = "description", nullable = false)
    private String description;
}
