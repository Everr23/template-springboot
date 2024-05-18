package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

import java.util.List;

@Data
@Builder
public class ProductDto {
    private Long id;
    @NotEmpty(message = "Product name should not be empty")
    private String name;
    @NotNull(message = "Product price should not be empty")
    @Min(value = 0, message = "Product price should not be negative")
    private Integer price;
    @NotNull(message = "Product stock should not be empty")
    @Min(value = 0, message = "Product stock should not be negative")
    private Integer stock;
    private String description;
    private List<BillDetailDto> billDetails;
}
