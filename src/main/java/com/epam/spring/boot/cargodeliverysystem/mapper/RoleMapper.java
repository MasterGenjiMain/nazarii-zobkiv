package com.epam.spring.boot.cargodeliverysystem.mapper;

import com.epam.spring.boot.cargodeliverysystem.dto.RoleDto;
import com.epam.spring.boot.cargodeliverysystem.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto mapRoleToRoleDto(Role role);

    Role mapRoleDtoToRole(RoleDto role);
}
