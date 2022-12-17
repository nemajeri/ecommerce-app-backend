//package com.server.ecommerceapp.configuration;
//
//import com.server.ecommerceapp.model.Product;
//import com.server.ecommerceapp.repository.ProductRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration("Configuration to populate DB table with mock products")
//@RequiredArgsConstructor
//public class ProductConfig {
//    @Bean
//    @Autowired
//    CommandLineRunner populateProductTable(ProductRepository productRepository)
//    {
//     return args -> {
//         Product tv = new Product(1L,
//                 "Samsung LC49RG90SSUXEN 49' Curved LED Gaming Monitor",
//                 1999.99,
//                 5,
//                 "samsung-monitor.jpg",
//                 "This tv is awesome"
//                 );
//         Product monitor = new Product(2L,
//                 "Samsung LC49RG90SSUXEN 49' Curved LED Gaming Monitor",
//                 1999.99,
//                 3,
//                 "samsung-monitor.jpg",
//                 "This tv is more then awesome!");
//         Product television = new Product(3L, "Samsung LC49RG90SSUXEN 49' Curved LED Gaming Monitor", 999.99,4, "samsung-monitor.jpg", "Damn, this tv is good!");
//
//         productRepository.saveAll(List.of(tv, monitor, television));
//        };
//    }
//}
