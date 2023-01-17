package com.server.ecommerceapp.service;


import com.server.ecommerceapp.dto.ProductDTO;
import com.server.ecommerceapp.exception.ProductAlreadyExistsException;
import com.server.ecommerceapp.exception.ProductNotFoundException;
import com.server.ecommerceapp.mapper.ProductMapper;
import com.server.ecommerceapp.model.Product;
import com.server.ecommerceapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
            getProducts();

        productRepository.findProductBySearchTerm(searchTerm).forEach(product -> products.add(productMapper.toProductDTO(product)));
        return products;
    }

    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();

        if(products.isEmpty())
            return new ArrayList<>(){};

        ModelMapper modelMapper = new ModelMapper();
        Type listType = new TypeToken<List<ProductDTO>>() {
        }.getType();
        List<ProductDTO> productDTOs = modelMapper.map(products, listType);

        return productDTOs;
    }

    public ProductDTO getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException("The product that user selected does not exist");
        }

        Product product = optionalProduct.get();
        ProductDTO productDTO = productMapper.toProductDTO(product);

        return productDTO;
    }

    public ProductDTO createProduct(ProductDTO productDTO) throws ProductAlreadyExistsException {
        Optional<Product> optionalProduct = productRepository.findProductByTitle(productDTO.getTitle());

        if (optionalProduct.isPresent()) {
            throw new ProductAlreadyExistsException("User selected a" + productDTO.getTitle() + " that already exists");
        }

        Product product = productMapper.toProduct(productDTO);
        Product savedProduct = productRepository.save(product);

        return new ProductDTO(savedProduct.getId(),
                savedProduct.getTitle(),
                savedProduct.getPrice(),
                savedProduct.getRating(),
                savedProduct.getImage(),
                savedProduct.getDescription());
    }

    public void deleteProduct(Long id) throws ProductNotFoundException {
        boolean exists = productRepository.existsById(id);

        if (!exists) {
            throw new ProductNotFoundException("User tried to delete product because it does not exist");
        }
        productRepository.deleteById(id);
    }


    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) throws ProductNotFoundException{
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("User tried to update " + productDTO.getTitle() + " that doesnt exist"));

        if (productDTO.getTitle() != null && productDTO.getTitle().length() > 0) {
            product.setTitle(productDTO.getTitle());
        }

        if (productDTO.getPrice() != null) {
            product.setPrice(productDTO.getPrice());
        }

        if (productDTO.getRating() != null) {
            product.setRating(productDTO.getRating());
        }

        if (productDTO.getImage() != null && productDTO.getImage().length() > 0) {
            product.setImage(productDTO.getImage());
        }

        if (productDTO.getDescription() != null && productDTO.getDescription().length() > 0) {
            product.setDescription(productDTO.getDescription());
        }

        product = productRepository.save(product);
        return productMapper.toProductDTO(product);
    }
}
