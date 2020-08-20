package com.bandg.users.dao;

import com.bandg.users.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fake")
public class FakeUserDataAccess implements  UserDao{
    private final List<User> db;

    public FakeUserDataAccess() {
        this.db = new ArrayList<>();
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

    @Override
    public int deleteUserByEmail(String Email) {
        return 0;
    }

    @Override
    public UserDetails loadUserByUsername(String Email) {
        return null;
    }

    @Override
    public User loadUser(String Email) {
        return null;
    }

    @Override
    public int activateAccount(String Email) {
        return 0;
    }

    @Override
    public int desActivateAccount(String Email) {
        return 0;
    }

    @Override
    public int lockAccount(String Email, boolean status) {
        return 0;
    }

    @Override
    public List<User> search(String keyword) {
        return null;
    }


    @Override
    public int setUserAsAdmin(String email) {
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public List<User> getAllUsers(String lastOneEmail) {
        return null;
    }
}
