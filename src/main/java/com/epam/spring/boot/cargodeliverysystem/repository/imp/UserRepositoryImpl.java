package com.epam.spring.boot.cargodeliverysystem.repository.imp;

import com.epam.spring.boot.cargodeliverysystem.exception.EntityNotFoundException;
import com.epam.spring.boot.cargodeliverysystem.model.User;
import com.epam.spring.boot.cargodeliverysystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final List<User> userList = new ArrayList<>();

    @Override
    public User getUser(String username) {
        log.info("[Repository] getUser by username {} ", username);
        return userList.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User createUser(User user) {
        log.info("[Repository] createUser {} ", user);
        userList.add(user);
        return user;
    }

    @Override
    public User updateUser(String username, User user) {
        log.info("[Repository] updateUser by username {} ", username);
        boolean isDeleted = userList.removeIf(u -> u.getUsername().equals(username));
        if(isDeleted) {
            userList.add(user);
        } else {
            throw new EntityNotFoundException();
        }
        return user;
    }

    @Override
    public boolean deleteUser(String username) {
        log.info("[Repository] deleteUser by username {} ", username);
        return userList.removeIf(u -> u.getUsername().equals(username));
    }
}
