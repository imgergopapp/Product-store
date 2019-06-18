package com.codecool.web.service.simple;

import com.codecool.web.dao.CartDao;
import com.codecool.web.model.CartItem;
import com.codecool.web.model.Product;
import com.codecool.web.service.CartService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SimpleCartService implements CartService {

    private final CartDao cartDao;

    public SimpleCartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    public List<CartItem> findAllByUserId(int userId) throws SQLException {
        return cartDao.findAllByUserId(userId);
    }

    @Override
    public void updateCart(int userId, Product product) throws SQLException {
        if( cartDao.isProductInCart(userId, product)){
            cartDao.incrementProductQuantity(userId,product);
        }
        else {
            cartDao.addProduct(userId, product);
        }

    }

    @Override
    public int getTotalPrice(int userId) throws ServiceException, SQLException {
        List<CartItem> cartItems = cartDao.findAllByUserId(userId);

        int totalPrice = 0;
        for (CartItem cartItem : cartItems){
            totalPrice += cartItem.getPrice();
        }
        return totalPrice;
    }

    @Override
    public CartItem findProductInCart(int userId, int productId) throws SQLException {
        return cartDao.findProductInCart(userId, productId);
    }
}
