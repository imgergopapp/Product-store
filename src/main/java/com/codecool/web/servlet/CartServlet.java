package com.codecool.web.servlet;

import com.codecool.web.dao.CartDao;
import com.codecool.web.dao.ProductDao;
import com.codecool.web.dao.database.DatabaseCartDao;
import com.codecool.web.dao.database.DatabaseProductDao;
import com.codecool.web.dto.CartDto;
import com.codecool.web.model.CartItem;
import com.codecool.web.model.Product;
import com.codecool.web.model.User;
import com.codecool.web.service.CartService;
import com.codecool.web.service.ProductService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleCartService;
import com.codecool.web.service.simple.SimpleProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/protected/cart")
public class CartServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection connection = getConnection(req.getServletContext())) {

            CartDao cartDao = new DatabaseCartDao(connection);
            CartService cartService = new SimpleCartService(cartDao);

            User loggedInUser = (User) req.getSession().getAttribute("user");
            int userId = loggedInUser.getId();

            List<CartItem> cartItems = cartService.findAllByUserId(userId);
            int totalPrice = cartService.getTotalPrice(userId);

            sendMessage(resp, HttpServletResponse.SC_OK, new CartDto(cartItems, totalPrice));
        } catch (SQLException | ServiceException se) {
            handleError(resp, se, true);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection connection = getConnection(req.getServletContext())) {

            ProductDao productDao = new DatabaseProductDao(connection);
            ProductService productService = new SimpleProductService(productDao);
            CartDao cartDao = new DatabaseCartDao(connection);
            CartService cartService = new SimpleCartService(cartDao);

            User loggedInUser = (User) req.getSession().getAttribute("user");
            int userId = loggedInUser.getId();

            int productId = Integer.parseInt(req.getParameter("productId"));
            Product product = productService.findById(productId);

            cartService.updateCart(userId, product);

            CartItem updatedCartItem = cartService.findProductInCart(userId,productId);


            sendMessage(resp, HttpServletResponse.SC_OK, updatedCartItem);
        } catch (SQLException se) {
            handleError(resp, se, true);
        }
    }
}
