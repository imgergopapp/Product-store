package com.codecool.web.service.simple;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Address;
import com.codecool.web.model.User;
import com.codecool.web.service.UserService;
import com.codecool.web.service.exception.ServiceException;

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

    @Override
    public User updateProfile(String name, String email, Address address) throws ServiceException, SQLException {
        if (isProfileChanged(null,null,null)){
            userDao.updateProfile(name, email, address);
            return findByEmail(email);
        }
        else {
            throw new ServiceException("Nothing saved! (No changes)");
        }

    }

    private boolean isProfileChanged(User user, String newName, Address newAddres){
       // if ()
        return true; // TODO : implement
    }


}
