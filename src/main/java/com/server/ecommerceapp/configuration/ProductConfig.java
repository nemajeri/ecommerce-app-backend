//package com.server.ecommerceapp.Product;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class ProductConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(IProductRepository productRepository)
//    {
//     return args -> {
//         Product tv = new Product(1,
//                 "Samsung LC49RG90SSUXEN 49' Curved LED Gaming Monitor",
//                 1999.99,
//                 5,
//                 "samsung-monitor.jpg",
//                 "This tv is awesome"
//                 );
//         Product monitor = new Product(2,
//                 "Samsung LC49RG90SSUXEN 49' Curved LED Gaming Monitor",
//                 1999.99,
//                 3,
//                 "samsung-monitor.jpg",
//                 "This tv is more then awesome!");
//         Product television = new Product(3, "Samsung LC49RG90SSUXEN 49' Curved LED Gaming Monitor", 999.99,4, "samsung-monitor.jpg", "Damn, this tv is good!");
//
//         productRepository.saveAll(List.of(tv, monitor, television));
//        };
//    }
//}
