package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.UserDto;

public interface UserService {

    UserDto getUser(String username);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(String username, UserDto userDto);

    boolean deleteUser(String username);
}
