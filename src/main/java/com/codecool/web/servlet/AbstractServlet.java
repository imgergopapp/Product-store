package com.codecool.web.servlet;

import com.codecool.web.dto.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

abstract class AbstractServlet extends HttpServlet {
    Connection getConnection(ServletContext sce) throws SQLException {
        DataSource dataSource = (DataSource) sce.getAttribute("dataSource");
        return dataSource.getConnection();
    }

    private final ObjectMapper om = new ObjectMapper();


    void sendMessage(HttpServletResponse resp, int status, String message) throws IOException {
        sendMessage(resp, status, new MessageDto(message));
    }

    void sendMessage(HttpServletResponse resp, int status, Object object) throws IOException {
        resp.setStatus(status);
        om.writeValue(resp.getOutputStream(), object);
    }

    void handleError(HttpServletResponse resp, Exception ex, boolean isServerSide) throws IOException {
        int responseStatusCode = isServerSide ? HttpServletResponse.SC_INTERNAL_SERVER_ERROR : HttpServletResponse.SC_BAD_REQUEST;
        sendMessage(resp, responseStatusCode , ex.getMessage());
        ex.printStackTrace();
    }
}
