package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.Role;

public interface RoleRepository {

    Role getRole(String roleName);

    Role addRole(Role role);

    Role updateRole(String roleName, Role role);

    boolean deleteRole(String roleName);
}
