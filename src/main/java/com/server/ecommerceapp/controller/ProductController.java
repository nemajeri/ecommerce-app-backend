package com.server.ecommerceapp.controller;

import com.server.ecommerceapp.dto.ProductDTO;
import com.server.ecommerceapp.service.ProductServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;


    @GetMapping()
    public ResponseEntity<List<ProductDTO>> GetAllProducts() {
        return ResponseEntity.ok(productServiceImpl.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> GetProductById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productServiceImpl.getProductById(id));
    }

    @PostMapping("/add-product")
    public ResponseEntity<ProductDTO> AddNewProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productServiceImpl.createProduct(productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> RemoveProduct(@PathVariable("id") Integer id) {
        productServiceImpl.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable("id") Integer id,
                              @RequestParam(required = false) String title,
                              @RequestParam(required = false) Double price,
                              @RequestParam(required = false) Integer rating,
                              @RequestParam(required = false) String image,
                              @RequestParam(required = false) String description) {
        productServiceImpl.updateProductProperties(id, title, price, rating, image, description);
    }
}
