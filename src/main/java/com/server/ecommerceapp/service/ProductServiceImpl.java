package com.server.ecommerceapp.service;


import com.server.ecommerceapp.dto.ProductDTO;
import com.server.ecommerceapp.mapper.ProductMapper;
import com.server.ecommerceapp.model.Product;
import com.server.ecommerceapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("Product Service Implementation")
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public List<ProductDTO> findProductsBySearchTerm(String searchTerm) {
        List<ProductDTO> products = new ArrayList<>();
        if(searchTerm.length() == 0)
            throw new IllegalArgumentException("Search term must not be empty.");

        productRepository.findProductBySearchTerm(searchTerm).forEach(product -> products.add(productMapper.toProductDTO(product)));
        return products;
    }

    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();

        if(products.isEmpty())
            throw new DataAccessException("No items were found in database") {};

        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<ProductDTO>>() {
        }.getType();
        List<ProductDTO> productDTOs = modelMapper.map(products, listType);

        return productDTOs;
    }

    public ProductDTO getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()) {
            throw new EntityNotFoundException("Product doesnt exist");
        }

        Product product = optionalProduct.get();
        ModelMapper modelMapper = new ModelMapper();
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

        return productDTO;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findProductByTitle(productDTO.getTitle());

        if (optionalProduct.isPresent()) {
            throw new IllegalStateException("Product already exists");
        }

        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setRating(productDTO.getRating());
        product.setImage(productDTO.getImage());
        product.setDescription(productDTO.getDescription());

        Product savedProduct = productRepository.save(product);

        return new ProductDTO(savedProduct.getId(),
                savedProduct.getTitle(),
                savedProduct.getPrice(),
                savedProduct.getRating(),
                savedProduct.getImage(),
                savedProduct.getDescription());
    }

    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Product doesnt exist");
        }
        productRepository.deleteById(id);
    }


    @Transactional
    public ProductDTO updateProductProperties(Long id,
                                              String title,
                                              Double price,
                                              Integer rating,
                                              String image,
                                              String description) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        if (title != null && title.length() > 0) {
            product.setTitle(title);
        }

        if (price != null) {
            product.setPrice(price);
        }

        if (rating != null) {
            product.setRating(rating);
        }

        if (image != null && image.length() > 0) {
            product.setImage(image);
        }

        if (description != null && description.length() > 0) {
            product.setDescription(description);
        }

        product = productRepository.save(product);
        return productMapper.toProductDTO(product);
    }
}
