package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.Address;
import com.codecool.web.model.User;
import com.codecool.web.service.UserService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleUserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/protected/profile")
public class ProfileServlet extends AbstractServlet {

    @Override
    protected void doPut (HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            UserService userService = new SimpleUserService(userDao);

            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String country = req.getParameter("country");
            String zipCode = req.getParameter("zipCode");
            String city = req.getParameter("city");
            String street = req.getParameter("street");

            Address address = new Address(country, zipCode, city, street);

            User user = userService.updateProfile(name, email, address);

            req.setAttribute("user", user);

            sendMessage(resp, HttpServletResponse.SC_OK, user);
        } catch (SQLException | ServiceException se) {
            handleError(resp, se, false);
        }
    }
}
