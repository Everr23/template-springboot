package com.example.demo.service.impl;

import jakarta.transaction.Transactional;

import com.example.demo.dto.UserDto;
import com.example.demo.models.Bill;
import com.example.demo.models.User;
import com.example.demo.repository.BillRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import static com.example.demo.mapper.UserMapper.mapToUser;
import static com.example.demo.mapper.UserMapper.mapToUserDto;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private BillRepository billRepository;

    public UserServiceImpl(UserRepository userRepository, BillRepository billRepository) {
        this.userRepository = userRepository;
        this.billRepository = billRepository;
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto findUserById(Long userId) {
        User user = userRepository.findById(userId).get();
        return mapToUserDto(user);
    }

    @Override
    public User saveUser(UserDto userDto) {
        User user = mapToUser(userDto);
        return userRepository.save(user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = mapToUser(userDto);
        userRepository.save(user);
    }

    @Override
    public void delete(Long userId) {
        User user = userRepository.findById(userId).get();
        List<Bill> bills = billRepository.findByUser(user);

        if (bills.isEmpty()) {
            userRepository.deleteById(userId);
        }
    }

}
