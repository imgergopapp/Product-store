package com.codecool.web.servlet;

import com.codecool.web.dao.ProductDao;
import com.codecool.web.dao.database.DatabaseProductDao;
import com.codecool.web.model.Product;
import com.codecool.web.service.ProductService;
import com.codecool.web.service.simple.SimpleProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/protected/allproducts")
public class allProductsServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            ProductDao productDao = new DatabaseProductDao(connection);
            ProductService productService = new SimpleProductService(productDao);

            boolean isOnSale = Boolean.valueOf(req.getParameter("isOnSale"));

            List<Product> products = productService.findAllProducts(isOnSale);
            sendMessage(resp, HttpServletResponse.SC_OK, products);
        } catch (SQLException se) {
            handleError(resp, se, false);
        }
    }
}
