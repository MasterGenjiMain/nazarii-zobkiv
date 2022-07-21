package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.api.UserApi;
import com.epam.spring.boot.cargodeliverysystem.dto.UserDto;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnCreate;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnUpdate;
import com.epam.spring.boot.cargodeliverysystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{username}")
    public UserDto getUser(@PathVariable String username) {
        log.info("[Controller] getUser {} by username ", username);
        return userService.getUser(username);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto createUser(@RequestBody @Validated(OnCreate.class) UserDto userDto) {
        log.info("[Controller] createUser {} ", userDto);
        return userService.createUser(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{username}")
    public UserDto updateUser(@PathVariable String username, @RequestBody @Validated(OnUpdate.class) UserDto userDto) {
        log.info("[Controller] updateUser by username {} ", username);
        return userService.updateUser(username, userDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{username}")
    public boolean deleteUser(@PathVariable String username) {
        log.info("[Controller] deleteUser by username {} ", username);
        return userService.deleteUser(username);
    }
}
