package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.models.User;
import com.example.demo.service.UserService;

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
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/users/create")
    public String createUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "users/create";
    }

    @PostMapping("/users/create")
    public String saveUser(@Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "users/create";
        }
        userService.saveUser(userDto);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}/edit")
    public String editUserForm(@PathVariable("userId") Long userId, Model model) {
        UserDto user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PostMapping("/users/{userId}/edit")
    public String updateUser(@PathVariable("userId") Long userId,
            @Valid @ModelAttribute("user") UserDto user,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "users/edit";
        }
        user.setId(userId);
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}/delete")
    public String deleteUser(@PathVariable("userId") Long userId) {
        userService.delete(userId);
        return "redirect:/users";
    }
}
