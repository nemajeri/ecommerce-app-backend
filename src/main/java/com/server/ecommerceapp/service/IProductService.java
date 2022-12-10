package com.server.ecommerceapp.service;

import com.server.ecommerceapp.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    public List<ProductDTO> findProductsBySearchTerm(String searchTerm);

    public List<ProductDTO> getProducts();

    public ProductDTO getProductById(Long id);

    public ProductDTO createProduct(ProductDTO productDTO);

    public void deleteProduct(Long id);

    public ProductDTO updateProductProperties(Long id,
                                        String title,
                                        Double price,
                                        Integer rating,
                                        String image,
                                        String description);
}
