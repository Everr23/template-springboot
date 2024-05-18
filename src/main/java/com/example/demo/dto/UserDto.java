package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Data
@Builder
public class UserDto {
    private Long id;
    @NotEmpty(message = "User name should not be empty")
    private String name;
    @NotEmpty(message = "User lastname should not be empty")
    private String lastname;
    @NotEmpty(message = "User email should not be empty")
    private String email;
    @NotEmpty(message = "User phone should not be empty")
    private String phone;
    private List<BillDto> bills;
}