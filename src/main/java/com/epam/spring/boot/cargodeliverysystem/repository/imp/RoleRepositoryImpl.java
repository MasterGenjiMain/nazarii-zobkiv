package com.epam.spring.boot.cargodeliverysystem.repository.imp;

import com.epam.spring.boot.cargodeliverysystem.model.Role;
import com.epam.spring.boot.cargodeliverysystem.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final List<Role> roleList = new ArrayList<>();

    @Override
    public Role getRole(String roleName) {
        log.info("[Repository] getRole by name {} ", roleName);
        return roleList.stream()
                .filter(r -> r.getRoleName().equals(roleName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role is not found!"));
    }

    @Override
    public Role addRole(Role role) {
        log.info("[Repository] addRole {} ", role);
        roleList.add(role);
        return role;
    }

    @Override
    public Role updateRole(String roleName, Role role) {
        log.info("[Repository] updateRole by name {} ", roleName);
        boolean isDeleted = roleList.removeIf(r -> r.getRoleName().equals(roleName));
        if(isDeleted) {
            roleList.add(role);
        } else {
            throw new RuntimeException("Role is not found!");
        }
        return role;
    }

    @Override
    public boolean deleteRole(String roleName) {
        log.info("[Repository] deleteRole by name {} ", roleName);
        return roleList.removeIf(r -> r.getRoleName().equals(roleName));
    }
}
