package com.epam.spring.boot.cargodeliverysystem.api;

import com.epam.spring.boot.cargodeliverysystem.dto.UserDto;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnCreate;
import com.epam.spring.boot.cargodeliverysystem.dto.group.OnUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "User management API")
@RequestMapping("/api/v1/users")
public interface UserApi {

    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", paramType = "path", required = true, value = "User's username")
    })
    @ApiOperation("GetUser")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{username}")
    UserDto getUser(@PathVariable String username);

    @ApiOperation("Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    UserDto createUser(@RequestBody @Validated(OnCreate.class) UserDto userDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", paramType = "path", required = true, value = "User's username")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{username}")
    UserDto updateUser(@PathVariable String username, @RequestBody @Validated(OnUpdate.class) UserDto userDto);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", paramType = "path", required = true, value = "User's username")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{username}")
    boolean deleteUser(@PathVariable String username);
}
