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
    public boolean isRegistered(String email, String password) throws SQLException {
        return userDao.isRegistered(email,password);
    }

    @Override
    public User updateProfile(String name, String email, Address address) throws ServiceException, SQLException {
        if (isProfileChanged(null,null,null) && isValidAddress(address)){
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

    private boolean isValidAddress(Address address) throws ServiceException{
        String msg = "";
        int zipCode = Integer.parseInt(address.getZipCode());
        if (isNotAlphabetic(address.getCountry())){
            msg+="Invalid country! Use only alphabetic characters!\n";
        }

        if (isNotAlphabetic(address.getCity())){
            msg+="Invalid city! Use only alphabetic characters!\n";
        }

        if (isNotNumeric(address.getZipCode())){
            msg+="Invalid zip code! Use only numbers!\n";
        }
        else if (zipCode < 0 || zipCode > 9999){
            msg+="Invalid zip code! It must be between 0 and 10 000!";
        }

        if (msg.equals("")){
            return true;
        }
        else{
            throw new ServiceException(msg);
        }
    }

    private boolean isNotAlphabetic(String word){
        for (char c : word.toCharArray()){
            if (!Character.isAlphabetic(c)){
                return true;
            }
        }
        return false;
    }

    private boolean isNotNumeric(String word){
        for (char c : word.toCharArray()){
            if (!Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }


}
