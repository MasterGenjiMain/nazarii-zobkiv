package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.UserDto;
import com.epam.spring.boot.cargodeliverysystem.model.User;
import com.epam.spring.boot.cargodeliverysystem.repository.UserRepository;
import com.epam.spring.boot.cargodeliverysystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUser(String username) {
        log.info("getUser by name {}", username);
        User user = userRepository.getUser(username);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("createUser with name {}", userDto.getUsername());
        User user = mapUserDtoToUser(userDto);
        user = userRepository.createUser(user);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(String username, UserDto userDto) {
        log.info("updateUser with name {}", username);
        User user = mapUserDtoToUser(userDto);
        user = userRepository.updateUser(username, user);
        return mapUserToUserDto(user);
    }

    @Override
    public boolean deleteUser(String username) {
        log.info("deleteUser with name {}", username);
        return userRepository.deleteUser(username);
    }

    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .createTime(user.getCreateTime())
                .roleId(user.getRoleId())
                .build();
    }

    private User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .createTime(userDto.getCreateTime())
                .roleId(userDto.getRoleId())
                .build();
    }
}
