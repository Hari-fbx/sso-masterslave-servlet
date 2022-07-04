package com.csvmodifer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csvmodifer.accounts.Users;

public class VerifyToken extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("verify token get");
        String data  = req.getParameter("sessionid").replaceAll(" ", "+");
        String status ="false";
        if(data==null){
            status="false";
        }
        else if(Users.activeUsers.contains(data)){
            status = "true";
        }
        resp.setContentType("text/html");
        System.out.println(Users.activeUsers.toString());
        PrintWriter out = resp.getWriter();
        // out.println(data);
        out.println(status);
    }
}
