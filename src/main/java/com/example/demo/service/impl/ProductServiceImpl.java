package com.example.demo.service.impl;

import com.example.demo.service.ProductService;

import jakarta.transaction.Transactional;

import com.example.demo.dto.ProductDto;
import com.example.demo.models.BillDetail;
import com.example.demo.models.Product;
import com.example.demo.repository.BillDetailRepository;
import com.example.demo.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import static com.example.demo.mapper.ProductMapper.mapToProduct;
import static com.example.demo.mapper.ProductMapper.mapToProductDto;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private BillDetailRepository billDetailRepository;

    public ProductServiceImpl(ProductRepository productRepository, BillDetailRepository billDetailRepository) {
        this.productRepository = productRepository;
        this.billDetailRepository = billDetailRepository;
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map((product) -> mapToProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDto findProductById(Long productId) {
        Product product = productRepository.findById(productId).get();
        return mapToProductDto(product);
    }

    @Override
    public Product saveProduct(ProductDto productDto) {
        Product product = mapToProduct(productDto);
        return productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        Product product = mapToProduct(productDto);
        productRepository.save(product);
    }

    @Override
    public void delete(Long productId) {
        Product product = productRepository.findById(productId).get();
        List<BillDetail> billsDetails = billDetailRepository.findByProduct(product);

        if (billsDetails.isEmpty()) {
            productRepository.deleteById(productId);
        }
    }

}
