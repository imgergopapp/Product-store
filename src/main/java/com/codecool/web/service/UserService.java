package com.codecool.web.service;

import com.codecool.web.model.Address;
import com.codecool.web.model.User;

import java.sql.SQLException;

public interface UserService {
    User findByEmail(String email) throws SQLException;

    User findById(int id) throws SQLException;

    void registerUser(String name, String password, String email, String role, Address address) throws SQLException;

    boolean isRegistered(String email, String password) throws SQLException;
}
