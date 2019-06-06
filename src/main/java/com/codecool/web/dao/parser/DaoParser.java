package com.codecool.web.dao.parser;

import com.codecool.web.model.Role;

import java.sql.SQLException;

public class DaoParser {

     public static Role parseToRole(String role) throws SQLException {
        Role parsedRole;
        switch (role){
            case "REGULAR":
                parsedRole = Role.REGULAR;
                break;
            case "ADMIN" :
                parsedRole = Role.ADMIN;
                break;
            case "RETAILER" :
                parsedRole = Role.RETAILER;
                break;
            default:
                throw new SQLException("Role can't be parsed from Result set!");
        }
        return parsedRole;
    }

}
