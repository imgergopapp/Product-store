package com.codecool.web.service;

import com.codecool.web.model.Address;
import com.codecool.web.model.User;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;

public interface UserService {
    User findById() throws SQLException;
    void registerUser(String name, String email, Address address) throws SQLException, ServiceException;// role is default : REGULAR
    boolean isEmailAvailable(String email) throws SQLException;
    boolean isRegistered(String email, String password) throws SQLException;
}
