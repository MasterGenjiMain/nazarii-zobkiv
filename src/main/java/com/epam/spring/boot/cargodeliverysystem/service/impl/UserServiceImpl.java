package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.UserDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.UserMapper;
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
    private final UserMapper userMapper;

    @Override
    public UserDto getUser(String username) {
        log.info("getUser by name {}", username);
        User user = userRepository.getUser(username);
        return userMapper.mapUserToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("createUser with name {}", userDto.getUsername());
        User user = userMapper.mapUserDtoToUser(userDto);
        user = userRepository.createUser(user);
        return userMapper.mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(String username, UserDto userDto) {
        log.info("updateUser with name {}", username);
        User user = userMapper.mapUserDtoToUser(userDto);

        User oldUser = userRepository.getUser(username);
        user.setEmail(oldUser.getEmail());
        user.setPassword(oldUser.getPassword());

        user = userRepository.updateUser(username, user);
        return userMapper.mapUserToUserDto(user);
    }

    @Override
    public boolean deleteUser(String username) {
        log.info("deleteUser with name {}", username);
        return userRepository.deleteUser(username);
    }
}
