package com.example.demo.service.impl;

import com.example.demo.service.BillDetailService;

import jakarta.transaction.Transactional;

import com.example.demo.dto.BillDetailDto;
import com.example.demo.models.Bill;
import com.example.demo.models.BillDetail;
import com.example.demo.models.Product;
import com.example.demo.repository.BillDetailRepository;
import com.example.demo.repository.BillRepository;
import com.example.demo.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import static com.example.demo.mapper.BillDetailMapper.mapToBillDetail;
import static com.example.demo.mapper.BillDetailMapper.mapToBillDetailDto;

@Service
@Transactional
public class BillDetailServiceImpl implements BillDetailService {
    private BillDetailRepository billDetailRepository;
    private ProductRepository productRepository;
    private BillRepository billRepository;

    public BillDetailServiceImpl(BillDetailRepository billDetailRepository,
            ProductRepository productRepository,
            BillRepository billRepository) {
        this.billDetailRepository = billDetailRepository;
        this.productRepository = productRepository;
        this.billRepository = billRepository;
    }

    @Override
    public List<BillDetailDto> findAllBillDetails() {
        List<BillDetail> billDetails = billDetailRepository.findAll();
        return billDetails.stream().map((billDetail) -> mapToBillDetailDto(billDetail)).collect(Collectors.toList());
    }

    @Override
    public BillDetailDto findBillDetailById(Long billDetailId) {
        BillDetail billDetail = billDetailRepository.findById(billDetailId).get();
        return mapToBillDetailDto(billDetail);
    }

    @Override
    public BillDetail saveBillDetail(Long billId, Long productId, BillDetailDto billDetailDto) {
        BillDetail billDetail = mapToBillDetail(billDetailDto);
        Bill bill = billRepository.findById(billId).get();
        Product product = productRepository.findById(productId).get();
        billDetail.setBill(bill);
        billDetail.setProduct(product);
        return billDetailRepository.save(billDetail);
    }

    @Override
    public void updateBillDetail(BillDetailDto billDetailDto) {
        BillDetail billDetail = mapToBillDetail(billDetailDto);
        billDetailRepository.save(billDetail);
    }

    @Override
    public void delete(Long billDetailId) {
        billDetailRepository.deleteById(billDetailId);
    }

}
