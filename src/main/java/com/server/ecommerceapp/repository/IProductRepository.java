package com.server.ecommerceapp.repository;

import com.server.ecommerceapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findProductByTitle(String Title);

}
