package com.server.ecommerceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {

    private Long id;

    private String title;

    private Double price;

    private Integer rating;

    private String image;

    private String description;
}
