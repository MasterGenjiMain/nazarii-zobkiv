package com.epam.spring.boot.cargodeliverysystem.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserDto {

    private long id;
    private String username;
    private String email;
    private String password;
    private String repeatPassword;
    private Date createTime;
    private long roleId;

}
