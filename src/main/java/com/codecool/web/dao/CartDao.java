package com.codecool.web.dao;

import com.codecool.web.model.CartItem;
import com.codecool.web.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface CartDao {

    List<CartItem> findAllByUserId(int userId) throws SQLException;

    CartItem findProductInCart(int userId, int productId) throws SQLException;

    boolean isProductInCart(int userId, Product product) throws SQLException;

    void addProduct(int userId, Product product) throws SQLException;

    void incrementProductQuantity(int userId, Product product) throws SQLException;

}
