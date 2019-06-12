package com.codecool.web.service.simple;

import com.codecool.web.dao.ProductDao;
import com.codecool.web.model.Product;
import com.codecool.web.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class SimpleProductService implements ProductService {

    private final ProductDao productDao;

    public SimpleProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product findById(int id) throws SQLException {
        return productDao.findById(id);
    }

    @Override
    public List<Product> findAllProducts(boolean isOnSale) throws SQLException {
        return productDao.findAllProducts(isOnSale);
    }
}
