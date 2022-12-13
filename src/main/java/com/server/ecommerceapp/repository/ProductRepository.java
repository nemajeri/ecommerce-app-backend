package com.server.ecommerceapp.repository;

import com.server.ecommerceapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByTitle(String Title);
    @Query("SELECT p FROM Product p WHERE p.title LIKE %:searchTerm%")
    List<Product> findProductBySearchTerm(@Param("searchTerm") String searchTerm);

}
