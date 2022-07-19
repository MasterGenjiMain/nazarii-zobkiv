package com.epam.spring.boot.cargodeliverysystem.service.impl;

import com.epam.spring.boot.cargodeliverysystem.dto.RoleDto;
import com.epam.spring.boot.cargodeliverysystem.mapper.RoleMapper;
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
    private final RoleMapper roleMapper;

    @Override
    public RoleDto getRole(String roleName) {
        log.info("getRole by name {}", roleName);
        Role role = roleRepository.getRole(roleName);
        return roleMapper.mapRoleToRoleDto(role);
    }

    @Override
    public RoleDto addRole(RoleDto roleDto) {
        log.info("addRole with name {}", roleDto.getRoleName());
        Role role = roleMapper.mapRoleDtoToRole(roleDto);
        role = roleRepository.addRole(role);
        return roleMapper.mapRoleToRoleDto(role);
    }

    @Override
    public RoleDto updateRole(String roleName, RoleDto roleDto) {
        log.info("updateRole with name {}", roleName);
        Role role = roleMapper.mapRoleDtoToRole(roleDto);

        Role oldRole = roleRepository.getRole(roleName);
        role.setId(oldRole.getId());

        role = roleRepository.updateRole(roleName, role);
        return roleMapper.mapRoleToRoleDto(role);
    }

    @Override
    public boolean deleteRole(String roleName) {
        log.info("deleteRole with name {}", roleName);
        return roleRepository.deleteRole(roleName);
    }
}
