package com.codecool.web.service.simple;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Address;
import com.codecool.web.model.User;
import com.codecool.web.service.UserService;

import java.sql.SQLException;

public class SimpleUserService implements UserService {

    private final UserDao userDao;

    public SimpleUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(int id) throws SQLException {
        return userDao.findById(id);
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        return userDao.findByEmail(email);
    }

    @Override
    public void registerUser(String name, String password, String email) throws SQLException {
        userDao.registerUser(name,password,email);
    }
}
