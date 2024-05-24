package com.example.demo.dto;

import com.example.demo.models.User;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillDto {
    private Long id;
    private Integer disccount;
    @NotNull(message = "Total should not be empty")
    @Min(value = 0, message = "Total should not be negative")
    private Integer total;
    private String comments;
    @NotNull(message = "User should not be empty")
    private User user;
    private List<BillDetailDto> billDetails;
    private Integer totalSold;
}