package com.example.demo.service;

import com.example.demo.dto.BillDto;
import com.example.demo.models.Bill;

import java.util.List;

public interface BillService {
    List<BillDto> findAllBills();

    BillDto findBillById(Long billId);

    Bill saveBill(Long userId, BillDto billDto);

    void updateBill(BillDto bill);

    void delete(Long billId);

    Integer getTotalBill(Long billId);

}
