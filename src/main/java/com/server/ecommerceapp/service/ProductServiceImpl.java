package com.server.ecommerceapp.service;


import com.server.ecommerceapp.dto.ProductDTO;
import com.server.ecommerceapp.model.Product;
import com.server.ecommerceapp.repository.IProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    private final ModelMapper modelMapper;

    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();

        Type listType = new TypeToken<List<ProductDTO>>() {}.getType();
        List<ProductDTO> productDTOs = modelMapper.map(products, listType);

        return productDTOs;
    }

    public ProductDTO getProductById(Integer id) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        if(!optionalProduct.isPresent()) {
            throw new EntityNotFoundException("Product doesnt exist");
        }

        Product product = optionalProduct.get();

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
    public void deleteProduct(Integer id) {
        boolean exists = productRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Product doesnt exist");
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public void updateProductProperties(Integer id,
                                        String title,
                                        Double price,
                                        Integer rating,
                                        String image,
                                        String description) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Product doesnt exist!"));

        if (title != null &&
                title.length() > 0 &&
                !Objects.equals(product.getTitle(), title)) {
            product.setTitle(title);
        }

        if (!Objects.equals(product.getPrice(), price)) {
            product.setPrice(price);
        }

        if (!Objects.equals(product.getRating(), rating)) {
            product.setRating(rating);
        }

        if (image != null &&
                image.length() > 0 &&
                !Objects.equals(product.getImage(), image)) {
            product.setImage(image);
        }

        if (description != null &&
                description.length() > 0 &&
                !Objects.equals(product.getDescription(), description)) {
            product.setDescription(description);
        }
    }
}
