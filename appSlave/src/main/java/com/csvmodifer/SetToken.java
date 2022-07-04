package com.csvmodifer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetToken extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("set token get");
        String session = req.getParameter("sessionid").replaceAll(" ","+");
        Cookie sessionid = new Cookie("sessionid",session);
        resp.addCookie(sessionid);
        resp.sendRedirect("http://localhost:8010/app");

    }
}
