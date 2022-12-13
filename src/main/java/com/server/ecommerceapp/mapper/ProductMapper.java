package com.server.ecommerceapp.mapper;

import com.server.ecommerceapp.dto.ProductDTO;
import com.server.ecommerceapp.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO toProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setRating(product.getRating());
        productDTO.setImage(product.getImage());
        productDTO.setDescription(product.getDescription());
        return productDTO;
    }
}
