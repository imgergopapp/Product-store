package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Address;
import com.codecool.web.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUserDao extends AbstractDao implements UserDao {

    public DatabaseUserDao(Connection connection) {
        super(connection);
    }

    @Override
    public User findById() throws SQLException {
        return null;
    }

    @Override
    public void registerUser(String name, String email, Address address) throws SQLException {

    }

    @Override
    public boolean isEmailAvailable(String email) throws SQLException {
        return false;
    }

    @Override
    public boolean isRegistered(String email, String password) throws SQLException {
        return false;
    }
}
