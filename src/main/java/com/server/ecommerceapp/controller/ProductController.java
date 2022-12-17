package com.server.ecommerceapp.controller;

import com.server.ecommerceapp.dto.ProductDTO;
import com.server.ecommerceapp.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("Controller for manipulating the products")
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private final ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> findProductsBySearchTerm(@RequestParam("searchTerm") String searchTerm ){
        return ResponseEntity.ok((productService.findProductsBySearchTerm(searchTerm)));
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> GetAllProducts() {
            logger.info("Handling get all products request");
            return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<ProductDTO> GetProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping(path ="/add-product")
    public ResponseEntity<ProductDTO> AddNewProduct(@RequestBody ProductDTO productDTO) {
        logger.info("Handling make a product in DB request");
        return ResponseEntity.ok(productService.createProduct(productDTO));
    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity<Object> RemoveProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path ="/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long id,
                                                    @RequestParam(required = false) String title,
                                                    @RequestParam(required = false) Double price,
                                                    @RequestParam(required = false) Integer rating,
                                                    @RequestParam(required = false) String image,
                                                    @RequestParam(required = false) String description) {

        return ResponseEntity.ok(productService.updateProductProperties(id, title, price, rating, image, description));
    }
}
