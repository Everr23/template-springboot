package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.models.User;

import java.util.stream.Collectors;

import static com.example.demo.mapper.BillMapper.mapToBillDto;

public class UserMapper {
    public static User mapToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .build();
    }

    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .phone(user.getPhone())
                .bills(user.getBills()
                        .stream()
                        .map((bill) -> mapToBillDto(bill))
                        .collect(Collectors.toList()))
                .build();
    }
}
