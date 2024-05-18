package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.models.User;

import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();

    UserDto findUserById(Long userId);

    User saveUser(UserDto userDto);

    void updateUser(UserDto user);

    void delete(Long userId);
}
