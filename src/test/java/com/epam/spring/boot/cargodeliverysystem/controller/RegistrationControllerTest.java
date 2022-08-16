package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.UserDto;
import com.epam.spring.boot.cargodeliverysystem.exception.PasswordsDidntMatchException;
import com.epam.spring.boot.cargodeliverysystem.service.RegistrationService;
import com.epam.spring.boot.cargodeliverysystem.test.util.TestDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

    @MockBean
    private RegistrationService registrationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUserTest() throws Exception {
        UserDto userDto = TestDataUtil.createUserDto();

        when(registrationService.registerUser(userDto)).thenReturn(userDto);

        mockMvc.perform(post("/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value(userDto.getUsername()));
    }

    @Test
    void createUserExceptionTest() throws Exception {
        UserDto userDto = TestDataUtil.createUserDto();

        when(registrationService.registerUser(userDto)).thenThrow(PasswordsDidntMatchException.class);

        mockMvc.perform(post("/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorType").value("VALIDATION_ERROR_TYPE"));
    }

    @Test
    void createUserShouldReturnErrorIfPasswordIsEmptyTest() throws Exception {
        UserDto userDto = TestDataUtil.createUserDto();
        userDto.setPassword("");
        String errorMessage = "'password' shouldn't be empty";
        String errorType = "VALIDATION_ERROR_TYPE";

        when(registrationService.registerUser(userDto)).thenReturn(userDto);

        mockMvc.perform(post("/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].message").value(errorMessage))
                .andExpect(jsonPath("$[0].errorType").value(errorType));
    }

    @Test
    void createUserShouldReturnValidationErrorIfPasswordHasNotEnoughLengthTest() throws Exception {
        UserDto userDto = TestDataUtil.createUserDto();
        userDto.setPassword("123");
        String errorMessage = "Password length isn't enough. Make it longer";
        String errorType = "VALIDATION_ERROR_TYPE";

        when(registrationService.registerUser(userDto)).thenReturn(userDto);

        mockMvc.perform(post("/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].message").value(errorMessage))
                .andExpect(jsonPath("$[0].errorType").value(errorType));
    }
}
