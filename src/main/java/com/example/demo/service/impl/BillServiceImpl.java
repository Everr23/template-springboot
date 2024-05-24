package com.example.demo.service.impl;

import com.example.demo.service.BillService;

import jakarta.transaction.Transactional;

import com.example.demo.dto.BillDto;
import com.example.demo.models.Bill;
import com.example.demo.models.BillDetail;
import com.example.demo.models.User;
import com.example.demo.repository.BillDetailRepository;
import com.example.demo.repository.BillRepository;
import com.example.demo.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import static com.example.demo.mapper.BillMapper.mapToBill;
import static com.example.demo.mapper.BillMapper.mapToBillDto;
import static com.example.demo.mapper.BillDetailMapper.mapToBillDetailDto;;

@Service
@Transactional
public class BillServiceImpl implements BillService {
    private BillRepository billRepository;
    private BillDetailRepository billDetailRepository;
    private UserRepository userRepository;

    public BillServiceImpl(BillRepository billRepository, BillDetailRepository billDetailRepository,
            UserRepository userRepository) {
        this.billRepository = billRepository;
        this.billDetailRepository = billDetailRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<BillDto> findAllBills() {
        List<Bill> bills = billRepository.findAll();

        return bills.stream().map((bill) -> {
            bill.setTotalSold(this.getTotalBill(bill.getId()));
            return mapToBillDto(bill);
        }).collect(Collectors.toList());
    }

    @Override
    public BillDto findBillById(Long billId) {
        Bill bill = billRepository.findById(billId).get();
        return mapToBillDto(bill);
    }

    @Override
    public Bill saveBill(Long userId, BillDto billDto) {
        Bill bill = mapToBill(billDto);
        User user = userRepository.findById(userId).get();
        bill.setUser(user);
        return billRepository.save(bill);
    }

    @Override
    public void updateBill(BillDto billDto) {
        Bill bill = mapToBill(billDto);
        billRepository.save(bill);
    }

    @Override
    public void delete(Long billId) {
        Bill bill = billRepository.findById(billId).get();
        List<BillDetail> billsDetails = billDetailRepository.findByBill(bill);

        if (billsDetails.isEmpty()) {
            billRepository.deleteById(billId);
        }
    }

    @Override
    public Integer getTotalBill(Long billId) {
        Integer sum = 0;
        Bill bill = billRepository.findById(billId).get();
        List<BillDetail> billsDetails = billDetailRepository.findByBill(bill);

        for (BillDetail billDetail : billsDetails) {
            sum += billDetail.getQuantity() * billDetail.getProduct().getPrice();
        }

        return sum;
    }

}
