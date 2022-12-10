package com.server.ecommerceapp.mapper;

import com.server.ecommerceapp.dto.ProductDTO;
import com.server.ecommerceapp.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IProductMapper {


    @Mapping(target = "id")
    @Mapping(target = "title")
    @Mapping(target = "price")
    @Mapping(target = "rating")
    @Mapping(target = "image")
    @Mapping(target = "description")
    ProductDTO toProductDTO(Product product);
}
