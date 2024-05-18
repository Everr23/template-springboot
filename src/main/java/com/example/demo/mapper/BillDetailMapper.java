package com.example.demo.mapper;

import com.example.demo.dto.BillDetailDto;
import com.example.demo.models.BillDetail;

public class BillDetailMapper {
    public static BillDetail mapToBillDetail(BillDetailDto billDetailDto) {
        return BillDetail.builder()
                .id(billDetailDto.getId())
                .quantity(billDetailDto.getQuantity())
                .bill(billDetailDto.getBill())
                .product(billDetailDto.getProduct())
                .build();
    }

    public static BillDetailDto mapToBillDetailDto(BillDetail billDetail) {
        return BillDetailDto.builder()
                .id(billDetail.getId())
                .quantity(billDetail.getQuantity())
                .bill(billDetail.getBill())
                .product(billDetail.getProduct())
                .build();
    }
}
