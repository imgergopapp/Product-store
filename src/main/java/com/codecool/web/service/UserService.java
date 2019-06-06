package com.codecool.web.service;

import com.codecool.web.model.Address;
import com.codecool.web.model.User;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;

public interface UserService {
    User findById(int id) throws SQLException;
    void registerUser(String name,String password, String email, String role, Address address) throws SQLException;
    boolean isEmailAvailable(String email) throws SQLException;
    boolean isRegistered(String email, String password) throws SQLException;
}
