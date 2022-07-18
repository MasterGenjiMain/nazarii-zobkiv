package com.epam.spring.boot.cargodeliverysystem.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class User {
    private long id;
    private String username;
    private String email;
    private String password;
    private Date createTime;
    private long roleId;
}
