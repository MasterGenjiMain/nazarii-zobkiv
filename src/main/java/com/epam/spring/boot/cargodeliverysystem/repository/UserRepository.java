package com.epam.spring.boot.cargodeliverysystem.repository;

import com.epam.spring.boot.cargodeliverysystem.model.User;

public interface UserRepository {

    User getUser(String username);

    User createUser(User user);

    User updateUser(String username, User user);

    boolean deleteUser(String username);
}
