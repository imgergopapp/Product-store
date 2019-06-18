package com.codecool.web.service;

import com.codecool.web.model.CartItem;
import com.codecool.web.model.Product;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface CartService {

    List<CartItem> findAllByUserId(int userId) throws SQLException;

    void updateCart(int userId, Product product) throws SQLException;

    int getTotalPrice(int userId) throws SQLException, ServiceException;

    CartItem findProductInCart(int userId, int productId) throws SQLException;

}
