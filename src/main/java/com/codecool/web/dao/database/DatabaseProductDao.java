package com.codecool.web.dao.database;

import com.codecool.web.dao.ProductDao;
import com.codecool.web.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseProductDao extends AbstractDao implements ProductDao {

    public DatabaseProductDao(Connection connection) {
        super(connection);
    }

    @Override
    public Product findById(int id) throws SQLException {
        String sql = "SELECT * FROM products " +
            "WHERE product_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            return fetchProduct(resultSet);
        }
    }

    @Override
    public List<Product> findAllProducts(boolean isOnSale) throws SQLException {
        String sql;
        if (isOnSale) {
            sql = "SELECT * FROM products WHERE sale_percentage > 0";
        } else {
            sql = "SELECT * FROM products ";
        }
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(fetchProduct(resultSet));
            }
            return products;
        }
    }

    private Product fetchProduct(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("product_id");
        String productName = resultSet.getString("product_name");
        String category = resultSet.getString("category");
        String properties = resultSet.getString("properties");
        int price = resultSet.getInt("product_price");
        int inStock = resultSet.getInt("in_stock");
        int salePercentage = resultSet.getInt("sale_percentage");
        String pictureUrl = resultSet.getString("picture_url");

        return new Product(id, productName, category, properties, price, inStock, salePercentage, pictureUrl);
    }
}
