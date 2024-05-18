package com.example.demo.dto;

import com.example.demo.models.Bill;
import com.example.demo.models.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillDetailDto {
    private Long id;
    @NotNull(message = "Quantity should not be empty")
    @Min(value = 0, message = "Quantity should not be negative")
    private Integer quantity;
    @NotNull(message = "Bill should not be empty")
    private Bill bill;
    @NotNull(message = "Product should not be empty")
    private Product product;
}
