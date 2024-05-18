package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.models.Product;

import java.util.stream.Collectors;

import static com.example.demo.mapper.BillDetailMapper.mapToBillDetailDto;

public class ProductMapper {
    public static Product mapToProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .stock(productDto.getStock())
                .description(productDto.getDescription())
                .build();
    }

    public static ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .billDetails(product.getBillDetails()
                        .stream()
                        .map((billDetail) -> mapToBillDetailDto(billDetail))
                        .collect(Collectors.toList()))
                .build();
    }
}
