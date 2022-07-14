package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.RoleDto;
import com.epam.spring.boot.cargodeliverysystem.model.Role;
import com.epam.spring.boot.cargodeliverysystem.repository.RoleRepository;
import com.epam.spring.boot.cargodeliverysystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleDto getRole(String roleName) {
        log.info("getRole by name {}", roleName);
        Role role = roleRepository.getRole(roleName);
        return mapRoleToRoleDto(role);
    }

    @Override
    public RoleDto addRole(RoleDto roleDto) {
        log.info("addRole with name {}", roleDto.getRoleName());
        Role role = mapRoleDtoToRole(roleDto);
        role = roleRepository.addRole(role);
        return mapRoleToRoleDto(role);
    }

    @Override
    public RoleDto updateRole(String roleName, RoleDto roleDto) {
        log.info("updateRole with name {}", roleName);
        Role role = mapRoleDtoToRole(roleDto);
        role = roleRepository.updateRole(roleName, role);
        return mapRoleToRoleDto(role);
    }

    @Override
    public boolean deleteRole(String roleName) {
        log.info("deleteRole with name {}", roleName);
        return roleRepository.deleteRole(roleName);
    }

    private RoleDto mapRoleToRoleDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();
    }

    private Role mapRoleDtoToRole(RoleDto role) {
        return Role.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .build();
    }
}
