package com.ambula.UserLocationAPI.service;

import com.ambula.UserLocationAPI.model.User;

public interface UserService {
    User getUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, User user);

    boolean deleteUser(Long id);

    Iterable<User> getAllUsers();
}


