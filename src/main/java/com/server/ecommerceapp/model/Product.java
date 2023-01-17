package com.server.ecommerceapp.model;

import javax.persistence.*;
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

    public Product(String title, Double price, Integer rating, String image, String description) {
        this.title = title;
        this.price = price;
        this.rating = rating;
        this.image = image;
        this.description = description;
    }
}
