package com.epam.spring.boot.cargodeliverysystem.dto;

import com.epam.spring.boot.cargodeliverysystem.customvalidator.annotation.PasswordConstraint;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnCreate;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnUpdate;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class UserDto {

    private long id;

    @NotBlank(message = "'username' shouldn't be empty", groups = OnCreate.class)
    private String username;

    @Email
    @Null(message = "'email' should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "'email' shouldn't be empty", groups = OnCreate.class)
    private String email;

    @NotBlank(message = "'password' shouldn't be empty", groups = OnCreate.class)
    @PasswordConstraint
    private String password;

    @NotBlank(message = "'repeatPassword' shouldn't be empty", groups = OnCreate.class)
    @PasswordConstraint
    private String repeatPassword;

    @NotNull(message = "'createTime' shouldn't be empty", groups = OnCreate.class)
    private Date createTime;

    @NotNull(message = "'roleId' shouldn't be empty", groups = OnCreate.class)
    private long roleId;

}
