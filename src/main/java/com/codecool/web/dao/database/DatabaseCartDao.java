package com.codecool.web.dao.database;

import com.codecool.web.dao.CartDao;
import com.codecool.web.model.CartItem;
import com.codecool.web.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCartDao extends AbstractDao implements CartDao {

    public DatabaseCartDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean isProductInCart(int userId, Product product) throws SQLException {
        String sql = "SELECT * FROM carts WHERE user_id = ? AND product_id = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, product.getProductId());
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            return resultSet.next();
        }
    }

    @Override
    public void addProduct(int userId, Product product) throws SQLException {
        String sql = "INSERT INTO carts (product_id, user_id, products_price) " +
            "VALUES(?, ?, ?); ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getProductId());
            statement.setInt(2, userId);
            statement.setInt(3, product.getPrice());
            statement.executeUpdate();
        }
    }

    @Override
    public void incrementProductQuantity(int userId, Product product) throws SQLException {
        String sql = "UPDATE carts SET products_price = products_price + ?, quantity = quantity + 1 " +
            "WHERE user_id = ? AND product_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getPrice());
            statement.setInt(2, userId);
            statement.setInt(3, product.getProductId());
            statement.executeUpdate();
        }
    }

    @Override
    public List<CartItem> findAllByUserId(int userId) throws SQLException {
        String sql = "SELECT p.product_name, c.quantity, c.products_price FROM carts AS c " +
            "JOIN products AS P ON c.product_id = p.product_id " +
            "WHERE c.user_id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            List<CartItem> cartItems = new ArrayList<>();
            while (resultSet.next()) {
                cartItems.add(fetchCartItem(resultSet));
            }
            return cartItems;
        }

    }

    @Override
    public CartItem findProductInCart(int userId, int productId) throws SQLException {
        String sql = "SELECT p.product_name, c.quantity, c.products_price FROM carts AS c " +
            "JOIN products AS P ON c.product_id = p.product_id " +
            "WHERE c.user_id = ? AND c.product_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            return fetchCartItem(resultSet);
        }
    }

    private CartItem fetchCartItem(ResultSet resultSet) throws SQLException {
        String productName = resultSet.getString("product_name");
        int quantity = resultSet.getInt("quantity");
        int price = resultSet.getInt("products_price");

        return new CartItem(productName, quantity, price);
    }
}
