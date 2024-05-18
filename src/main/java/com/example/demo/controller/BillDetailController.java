package com.example.demo.controller;

import com.example.demo.dto.BillDetailDto;
import com.example.demo.dto.BillDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.mapper.BillMapper;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.models.BillDetail;
import com.example.demo.service.BillDetailService;
import com.example.demo.service.BillService;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class BillDetailController {
    private BillDetailService billDetailService;
    private ProductService productService;
    private BillService billService;

    public BillDetailController(BillDetailService billDetailService, ProductService productService,
            BillService billService) {
        this.billDetailService = billDetailService;
        this.productService = productService;
        this.billService = billService;
    }

    @GetMapping("/billsdetails")
    public String listBillDetails(Model model) {
        List<BillDetailDto> billDetails = billDetailService.findAllBillDetails();
        model.addAttribute("billDetails", billDetails);
        return "billsdetails/list";
    }

    @GetMapping("/billsdetails/create")
    public String createBillDetailForm(Model model) {
        BillDetail billDetail = new BillDetail();

        List<BillDto> bills = billService.findAllBills();
        List<ProductDto> products = productService.findAllProducts();

        model.addAttribute("billDetail", billDetail);
        model.addAttribute("bills", bills);
        model.addAttribute("bill", null);
        model.addAttribute("products", products);
        model.addAttribute("product", null);

        return "billsdetails/create";
    }

    @PostMapping("/billsdetails/create")
    public String saveBillDetail(@Valid @ModelAttribute("billDetail") BillDetailDto billDetailDto,
            @Valid @ModelAttribute("bill") Long bill,
            @Valid @ModelAttribute("product") Long product,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            List<BillDto> bills = billService.findAllBills();
            List<ProductDto> products = productService.findAllProducts();

            model.addAttribute("billDetail", billDetailDto);
            model.addAttribute("bills", bills);
            model.addAttribute("bill", bill);
            model.addAttribute("products", products);
            model.addAttribute("product", product);

            return "billsdetails/create";
        }
        billDetailService.saveBillDetail(bill, product, billDetailDto);
        return "redirect:/billsdetails";
    }

    @GetMapping("/billsdetails/{billDetailId}/edit")
    public String editBillDetailForm(@PathVariable("billDetailId") Long billDetailId, Model model) {
        BillDetailDto billDetail = billDetailService.findBillDetailById(billDetailId);

        List<BillDto> bills = billService.findAllBills();
        List<ProductDto> products = productService.findAllProducts();

        Long bill = billDetail.getBill().getId();
        Long product = billDetail.getProduct().getId();

        model.addAttribute("billDetail", billDetail);
        model.addAttribute("bills", bills);
        model.addAttribute("bill", bill);
        model.addAttribute("products", products);
        model.addAttribute("product", product);

        return "billsdetails/edit";
    }

    @PostMapping("/billsdetails/{billDetailId}/edit")
    public String updateBillDetail(@PathVariable("billDetailId") Long billDetailId,
            @Valid @ModelAttribute("billDetail") BillDetailDto billDetail,
            @Valid @ModelAttribute("bill") Long bill,
            @Valid @ModelAttribute("product") Long product,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            List<BillDto> bills = billService.findAllBills();
            List<ProductDto> products = productService.findAllProducts();

            model.addAttribute("billDetail", billDetail);
            model.addAttribute("bills", bills);
            model.addAttribute("bill", bill);
            model.addAttribute("products", products);
            model.addAttribute("product", product);

            return "billsdetails/edit";
        }
        ProductDto productDto = productService.findProductById(product);
        BillDto billDto = billService.findBillById(bill);

        billDetail.setId(billDetailId);
        billDetail.setBill(BillMapper.mapToBill(billDto));
        billDetail.setProduct(ProductMapper.mapToProduct(productDto));

        billDetailService.updateBillDetail(billDetail);
        return "redirect:/billsdetails";
    }

    @GetMapping("/billsdetails/{billDetailId}/delete")
    public String deleteBillDetail(@PathVariable("billDetailId") Long billDetailId) {
        billDetailService.delete(billDetailId);
        return "redirect:/billsdetails";
    }
}
