package com.server.ecommerceapp.configuration;

import com.server.ecommerceapp.model.Product;
import com.server.ecommerceapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration("Configuration to populate DB table with mock products")
@RequiredArgsConstructor
public class ProductConfig {
    @Bean
    CommandLineRunner populateProductTable(ProductRepository productRepository)
    {
     return args -> {
         Product tv = new Product(
                 "Xbox Series X",
                 1999.99,
                 5,
                 "xbox-console.jpg",
                 "This console is awesome!"
                 );
         Product monitor = new Product(
                 "Samsung LC49RG90SSUXEN 52' Curved LED Gaming Monitor",
                 1999.99,
                 3,
                 "samsung-monitor.jpg",
                 "This monitor is more then awesome!");
         Product television = new Product( "Iphone X Black Edition", 999.99,4, "iphone-smartphone.jpg", "Pre-owned product has been professionally inspected, tested and cleaned by Amazon qualified vendors. It is not certified by Apple.");

         productRepository.saveAll(List.of(tv, monitor, television));
        };
    }
}
