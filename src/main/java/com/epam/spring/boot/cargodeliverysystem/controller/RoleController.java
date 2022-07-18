package com.epam.spring.boot.cargodeliverysystem.controller;

import com.epam.spring.boot.cargodeliverysystem.dto.RoleDto;
import com.epam.spring.boot.cargodeliverysystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{roleName}")
    public RoleDto getRole(@PathVariable String roleName) {
        log.info("[Controller] getRole by name {} ", roleName);
        return roleService.getRole(roleName);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RoleDto addRole(@RequestBody RoleDto roleDto) {
        log.info("[Controller] addRole by name {} ", roleDto);
        return roleService.addRole(roleDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{roleName}")
    public RoleDto updateRole(@PathVariable String roleName, @RequestBody RoleDto roleDto) {
        log.info("[Controller] updateRole by name {} ", roleName);
        return roleService.updateRole(roleName, roleDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{roleName}")
    public boolean deleteRole(@PathVariable String roleName) {
        log.info("[Controller] deleteRole by name {} ", roleName);
        return roleService.deleteRole(roleName);
    }
}
