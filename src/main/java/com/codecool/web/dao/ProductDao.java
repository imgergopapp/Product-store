package com.codecool.web.dao;

import com.codecool.web.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    Product findById(int id) throws SQLException;

    List<Product> findAllProducts(boolean isOnSale) throws SQLException;

}
