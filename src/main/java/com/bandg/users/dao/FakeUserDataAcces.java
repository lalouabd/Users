package com.bandg.users.dao;

import com.bandg.users.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("fake")
public class FakeUserDataAcces implements  UserDao{
    private final List<User> db;

    public FakeUserDataAcces() {
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
    public User getUserByEmail(String Email) {
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
