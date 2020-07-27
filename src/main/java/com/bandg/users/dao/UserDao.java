package com.bandg.users.dao;

import com.bandg.users.models.User;

import java.util.UUID;

public interface UserDao {
    int insertUser(User user);
    int deleteUserById(UUID id);
    int deleteUserByEmail(String Email);

}
