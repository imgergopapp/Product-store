package com.codecool.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/protected/logout")
public final class LogoutServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
