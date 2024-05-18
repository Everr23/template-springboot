package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Bill;
import com.example.demo.models.BillDetail;
import com.example.demo.models.Product;

import java.util.List;

public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
    List<BillDetail> findByProduct(Product product);

    List<BillDetail> findByBill(Bill bill);
}
