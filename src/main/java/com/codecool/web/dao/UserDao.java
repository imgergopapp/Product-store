package com.codecool.web.dao;

import com.codecool.web.model.Address;
import com.codecool.web.model.User;

import java.sql.SQLException;

public interface UserDao {
    User findById(int id) throws SQLException;
    void registerUser(String name,String password, String email, String role, Address address) throws SQLException;
    boolean isRegistered(String email, String password) throws SQLException;
}
