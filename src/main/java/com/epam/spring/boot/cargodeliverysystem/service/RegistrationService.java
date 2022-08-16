package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.UserDto;

public interface RegistrationService {

    UserDto registerUser(UserDto userDto);
}
