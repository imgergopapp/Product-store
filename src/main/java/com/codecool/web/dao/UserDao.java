package com.codecool.web.dao;

import com.codecool.web.model.User;

import java.sql.SQLException;

public interface UserDao {
    User findById(int id) throws SQLException;

    User findByEmail(String email) throws SQLException;

    void registerUser(String name, String password, String email) throws SQLException;

    boolean isRegistered(String email, String password) throws SQLException;
}
