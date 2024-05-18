package com.example.demo.service;

import com.example.demo.dto.BillDetailDto;
import com.example.demo.models.BillDetail;

import java.util.List;

public interface BillDetailService {
    List<BillDetailDto> findAllBillDetails();

    BillDetailDto findBillDetailById(Long billDetailId);

    BillDetail saveBillDetail(Long billId, Long productId, BillDetailDto billDetailDto);

    void updateBillDetail(BillDetailDto billDetail);

    void delete(Long billDetailId);
}
