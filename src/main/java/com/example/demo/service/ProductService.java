package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.models.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAllProducts();

    ProductDto findProductById(Long productId);

    Product saveProduct(ProductDto productDto);

    void updateProduct(ProductDto product);

    void delete(Long productId);
}
