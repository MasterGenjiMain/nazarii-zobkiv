package com.epam.spring.boot.cargodeliverysystem.service;

import com.epam.spring.boot.cargodeliverysystem.dto.RoleDto;

public interface RoleService {

    RoleDto getRole(String roleName);

    RoleDto addRole(RoleDto roleDto);

    RoleDto updateRole(String roleName, RoleDto roleDto);

    boolean deleteRole(String roleName);
}
