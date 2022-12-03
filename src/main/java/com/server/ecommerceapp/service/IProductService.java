package com.server.ecommerceapp.service;

import com.server.ecommerceapp.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    public List<ProductDTO> getProducts();

    public ProductDTO getProductById(Integer id);

    public ProductDTO createProduct(ProductDTO productDTO);

    public void deleteProduct(Integer id);

    public void updateProductProperties(Integer id,
                                        String title,
                                        Double price,
                                        Integer rating,
                                        String image,
                                        String description);
}
