package com.example.demo.controller;

import com.example.demo.dto.BillDto;
import com.example.demo.dto.UserDto;
import com.example.demo.models.Bill;
import com.example.demo.service.BillService;
import com.example.demo.service.UserService;
import com.example.demo.mapper.UserMapper;

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
public class BillController {
    private BillService billService;
    private UserService userService;

    public BillController(BillService billService, UserService userService) {
        this.billService = billService;
        this.userService = userService;
    }

    @GetMapping("/bills")
    public String listBills(Model model) {
        List<BillDto> bills = billService.findAllBills();
        model.addAttribute("bills", bills);
        return "bills/list";
    }

    @GetMapping("/bills/create")
    public String createBillForm(Model model) {
        Bill bill = new Bill();
        List<UserDto> users = userService.findAllUsers();

        model.addAttribute("bill", bill);
        model.addAttribute("users", users);
        model.addAttribute("user", null);

        return "bills/create";
    }

    @PostMapping("/bills/create")
    public String saveBill(@Valid @ModelAttribute("bill") BillDto billDto,
            @Valid @ModelAttribute("user") Long user,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            List<UserDto> users = userService.findAllUsers();

            model.addAttribute("bill", billDto);
            model.addAttribute("users", users);
            model.addAttribute("user", user);

            return "bills/create";
        }
        billService.saveBill(user, billDto);
        return "redirect:/bills";
    }

    @GetMapping("/bills/{billId}/edit")
    public String editBillForm(@PathVariable("billId") Long billId, Model model) {
        BillDto bill = billService.findBillById(billId);

        List<UserDto> users = userService.findAllUsers();
        Long user = bill.getUser().getId();

        model.addAttribute("bill", bill);
        model.addAttribute("users", users);
        model.addAttribute("user", user);

        return "bills/edit";
    }

    @PostMapping("/bills/{billId}/edit")
    public String updateBill(@PathVariable("billId") Long billId,
            @Valid @ModelAttribute("bill") BillDto bill,
            @Valid @ModelAttribute("user") Long user,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            List<UserDto> users = userService.findAllUsers();

            model.addAttribute("bill", bill);
            model.addAttribute("users", users);
            model.addAttribute("user", user);

            return "bills/edit";
        }
        UserDto userDto = userService.findUserById(user);

        bill.setId(billId);
        bill.setUser(UserMapper.mapToUser(userDto));

        billService.updateBill(bill);
        return "redirect:/bills";
    }

    @GetMapping("/bills/{billId}/delete")
    public String deleteBill(@PathVariable("billId") Long billId) {
        billService.delete(billId);
        return "redirect:/bills";
    }
}
