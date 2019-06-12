package com.codecool.web.service;

import com.codecool.web.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    Product findById(int id) throws SQLException;

    List<Product> findAllProducts(boolean isOnSale) throws SQLException;

}
