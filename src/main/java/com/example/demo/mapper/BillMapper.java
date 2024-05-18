package com.example.demo.mapper;

import com.example.demo.dto.BillDto;
import com.example.demo.models.Bill;

import java.util.stream.Collectors;

import static com.example.demo.mapper.BillDetailMapper.mapToBillDetailDto;

public class BillMapper {
    public static Bill mapToBill(BillDto billDto) {
        return Bill.builder()
                .id(billDto.getId())
                .disccount(billDto.getDisccount())
                .total(billDto.getTotal())
                .comments(billDto.getComments())
                .user(billDto.getUser())
                .build();
    }

    public static BillDto mapToBillDto(Bill bill) {
        return BillDto.builder()
                .id(bill.getId())
                .disccount(bill.getDisccount())
                .total(bill.getTotal())
                .comments(bill.getComments())
                .user(bill.getUser())
                .billDetails(bill.getBillDetails()
                        .stream()
                        .map((billDetail) -> mapToBillDetailDto(billDetail))
                        .collect(Collectors.toList()))
                .build();
    }
}
