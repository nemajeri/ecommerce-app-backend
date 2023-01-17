package com.server.ecommerceapp.controller;

import com.server.ecommerceapp.dto.ProductDTO;
import com.server.ecommerceapp.exception.ProductAlreadyExistsException;
import com.server.ecommerceapp.exception.ProductNotFoundException;
import com.server.ecommerceapp.service.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;

@RestController("Controller for manipulating the products")
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> findProductsBySearchTerm(@RequestParam("searchTerm") String searchTerm ){
        return ResponseEntity.ok((productService.findProductsBySearchTerm(searchTerm)));
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
            return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(productService.getProductById(id));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path ="/add-product")
    public ResponseEntity<ProductDTO> addNewProduct(@RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.ok(productService.createProduct(productDTO));
        } catch (ProductAlreadyExistsException e) {
            return new ResponseEntity<>(CONFLICT);
        }
    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity<Object> removeProduct(@PathVariable("id") Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path ="/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long id,
                                                    @RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.ok(productService.updateProduct(id, productDTO));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
