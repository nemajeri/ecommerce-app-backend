package com.server.ecommerceapp.service;

import com.server.ecommerceapp.dto.ProductDTO;
import com.server.ecommerceapp.exception.ProductAlreadyExistsException;
import com.server.ecommerceapp.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {

     List<ProductDTO> findProductsBySearchTerm(String searchTerm);

     List<ProductDTO> getProducts();

     ProductDTO getProductById(Long id) throws ProductNotFoundException;

     ProductDTO createProduct(ProductDTO productDTO) throws ProductAlreadyExistsException;

     void deleteProduct(Long id) throws ProductNotFoundException;

     ProductDTO updateProduct(Long id, ProductDTO productDTO) throws ProductNotFoundException;
}
