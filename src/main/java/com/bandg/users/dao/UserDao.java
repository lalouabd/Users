package com.bandg.users.dao;

import com.bandg.users.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import  java.util.List;

public interface UserDao {
    int insertUser(User user);
    int deleteUserByEmail(String Email);
    UserDetails loadUserByUsername(String Email);
    User loadUser(String Email);
    int activateAccount(String Email);
    int desActivateAccount(String Email);
    int lockAccount(String Email, boolean status);
    List<User> search(String keyword);
    int setUserAsAdmin(String email);
    int updateUser(User user);
    List<User> getAllUsers(String lastOneEmail);
}
