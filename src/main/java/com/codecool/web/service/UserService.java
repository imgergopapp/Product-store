package com.codecool.web.service;

import com.codecool.web.model.Address;
import com.codecool.web.model.User;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;

public interface UserService {
    User findByEmail(String email) throws SQLException;

    User findById(int id) throws SQLException;

    void registerUser(String name, String password, String email) throws SQLException;

    User updateProfile(String name, String email, Address address) throws ServiceException, SQLException;

}
