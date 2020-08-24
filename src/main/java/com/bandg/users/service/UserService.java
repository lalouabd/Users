package com.bandg.users.service;

import com.bandg.users.dao.UserDao;
import com.bandg.users.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private  final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(@Qualifier("userPostgres") UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    public int insertUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.insertUser(user);
    }

       public int deleteUserByEmail(String Email) {
        return userDao.deleteUserByEmail(Email);
    }

    public UserDetails loadUserByUsername(String Email) {
        return userDao.loadUserByUsername(Email);
    }


    public User loadUser(String Email) {
        return userDao.loadUser(Email);
    }


    public int activateAccount(String Email) {
        return userDao.activateAccount(Email);
    }


    public int desActivateAccount(String Email) {
        return userDao.desActivateAccount(Email);
    }


    public int lockAccount(String Email, boolean status) {
        return userDao.lockAccount(Email,status);
    }


    public List<User> search(String keyword) {
        return userDao.search(keyword);
    }


    public int setUserAsAdmin(String email) {
        return userDao.setUserAsAdmin(email);
    }


    public int updateUser(User user) {
        return userDao.updateUser(user);
    }


    public List<User> getAllUsers(String lastOneEmail) {
        return userDao.getAllUsers(lastOneEmail);
    }
}
