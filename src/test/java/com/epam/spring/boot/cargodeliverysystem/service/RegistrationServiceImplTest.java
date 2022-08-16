package com.epam.spring.boot.cargodeliverysystem.service;

import static com.epam.spring.boot.cargodeliverysystem.test.util.TestDataUtil.TEST_EMAIL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.epam.spring.boot.cargodeliverysystem.dto.RoleDto;
import com.epam.spring.boot.cargodeliverysystem.dto.UserDto;
import com.epam.spring.boot.cargodeliverysystem.exception.PasswordsDidntMatchException;
import com.epam.spring.boot.cargodeliverysystem.exception.UserAlreadyExistsException;
import com.epam.spring.boot.cargodeliverysystem.mapper.UserMapper;
import com.epam.spring.boot.cargodeliverysystem.mapper.UserMapperImpl;
import com.epam.spring.boot.cargodeliverysystem.model.User;
import com.epam.spring.boot.cargodeliverysystem.repository.RoleRepository;
import com.epam.spring.boot.cargodeliverysystem.repository.UserRepository;
import com.epam.spring.boot.cargodeliverysystem.service.impl.RegistrationServiceImpl;
import com.epam.spring.boot.cargodeliverysystem.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceImplTest {

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Spy
    private final UserMapper userMapper = new UserMapperImpl();
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    @Test
    void registerUserTest() {
        User testUser = TestDataUtil.createUser();
        UserDto testUserDto = TestDataUtil.createUserDto();
        RoleDto testRoleDto = TestDataUtil.createRoleDto();
        when(roleRepository.findById(any())).thenReturn(Optional.of(TestDataUtil.createRole()));
        when(userRepository.save(any())).thenReturn(testUser);

        UserDto userDto = registrationService.registerUser(testUserDto);
        assertThat(userDto, allOf(
            hasProperty("username", equalTo(testUser.getUsername())),
                hasProperty("email", equalTo(testUser.getEmail())),
                hasProperty("password", equalTo(testUser.getPassword())),
                hasProperty("createTime", equalTo(testUser.getCreateTime())),
                hasProperty("role", equalTo(testRoleDto))
        ));
    }

    @Test
    void registerUserUserAlreadyExistsExceptionTest(){
        UserDto userDto = TestDataUtil.createUserDto();
        when(userRepository.existsByEmail(TEST_EMAIL)).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> registrationService.registerUser(userDto));
    }

    @Test
    void registerUserPasswordsDidntMatchExceptionTest(){
        UserDto userDto = TestDataUtil.createUserDto();
        userDto.setRepeatPassword("wrongRepeatPassword");

        assertThrows(PasswordsDidntMatchException.class, () -> registrationService.registerUser(userDto));
    }

}
