package com.server.ecommerceapp.controller;

import com.server.ecommerceapp.dto.ProductDTO;
import com.server.ecommerceapp.service.ProductServiceImpl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/products")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping(value = "/search")
    public ResponseEntity<List<ProductDTO>> findProductsBySearchTerm(@RequestParam("searchTerm") String searchTerm ){
        return ResponseEntity.ok((productServiceImpl.findProductsBySearchTerm(searchTerm)));
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> GetAllProducts() {
            logger.info("Handling get all products request");
            return ResponseEntity.ok(productServiceImpl.getProducts());
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<ProductDTO> GetProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productServiceImpl.getProductById(id));
    }

    @PostMapping(value ="/add-product")
    public ResponseEntity<ProductDTO> AddNewProduct(@RequestBody ProductDTO productDTO) {
        logger.info("Handling make a product in DB request");
        return ResponseEntity.ok(productServiceImpl.createProduct(productDTO));
    }

    @DeleteMapping(value ="/{id}")
    public ResponseEntity<Object> RemoveProduct(@PathVariable("id") Long id) {
        productServiceImpl.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long id,
                                                    @RequestParam(required = false) String title,
                                                    @RequestParam(required = false) Double price,
                                                    @RequestParam(required = false) Integer rating,
                                                    @RequestParam(required = false) String image,
                                                    @RequestParam(required = false) String description) {

        return ResponseEntity.ok(productServiceImpl.updateProductProperties(id, title, price, rating, image, description));
    }
}
