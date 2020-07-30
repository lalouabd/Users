package com.bandg.users.dao;

import com.bandg.users.models.User;
import  java.util.List;

public interface UserDao {
    int insertUser(User user);

    int deleteUserByEmail(String Email);

    User getUserByEmail(String Email);

    int setUserAsAdmin(String email);

    int updateUser(User user);

    List<User> getAllUsers(String lastOneEmail);
}
