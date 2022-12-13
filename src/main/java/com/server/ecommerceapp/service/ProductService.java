package com.server.ecommerceapp.service;

import com.server.ecommerceapp.dto.ProductDTO;

import java.util.List;

public interface ProductService {

     List<ProductDTO> findProductsBySearchTerm(String searchTerm);

     List<ProductDTO> getProducts();

     ProductDTO getProductById(Long id);

     ProductDTO createProduct(ProductDTO productDTO);

     void deleteProduct(Long id);

     ProductDTO updateProductProperties(Long id,
                                        String title,
                                        Double price,
                                        Integer rating,
                                        String image,
                                        String description);
}
