package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.UserDto;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnCreate;
import com.epam.spring.boot.cargodeliverysystem.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto createUser(@RequestBody @Validated(OnCreate.class) UserDto userDto) {
        log.info("[RegistrationController] createUser {} ", userDto);
        return registrationService.registerUser(userDto);
    }
}
