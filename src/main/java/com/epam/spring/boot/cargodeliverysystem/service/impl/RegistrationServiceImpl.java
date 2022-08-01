package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.UserDto;
import com.epam.spring.boot.cargodeliverysystem.exception.EntityNotFoundException;
import com.epam.spring.boot.cargodeliverysystem.exception.UserAlreadyExistsException;
import com.epam.spring.boot.cargodeliverysystem.mapper.UserMapper;
import com.epam.spring.boot.cargodeliverysystem.model.Role;
import com.epam.spring.boot.cargodeliverysystem.model.User;
import com.epam.spring.boot.cargodeliverysystem.repository.RoleRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.UserRepository;
import com.epam.spring.boot.cargodeliverysystem.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto registerUser(UserDto userDto) {
        log.info("[RegistrationServiceImpl] userRegistration with email {}", userDto.getEmail());
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        User user = userMapper.mapUserDtoToUser(userDto);
        Role role = roleRepository.findById(1L)
                .orElseThrow(EntityNotFoundException::new);
        user.setRole(role);

        encryptUserPassword(user);

        user = userRepository.save(user);
        log.info("[RegistrationServiceImpl] user with email {} successfully created", user.getEmail());
        return userMapper.mapUserToUserDto(user);
    }

    private void encryptUserPassword(User user) {
        String basicPassword = user.getPassword();
        try {
            user.setPassword(PasswordEncryptionService.toHexString(PasswordEncryptionService.getSHA(basicPassword)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
