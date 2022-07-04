package com.csvmodifer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csvmodifer.accounts.Users;
import com.csvmodifer.util.Util;
public class GetToken extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get token get");
        Cookie cookie = Util.getCookie(req,"sessionid");
        PrintWriter out =  resp.getWriter();
        if(cookie!=null){
            System.out.println("is null");
            resp.setContentType("text/html");
            out.println(cookie.getValue());
            if(Users.activeUsers.contains(cookie.getValue())){
                System.out.print("contains token");
                resp.sendRedirect("http://localhost:8010/app/settoken?sessionid="+cookie.getValue());
            }else{
                resp.sendRedirect("http://localhost:8085/app/login");
            }
        }else{
            resp.sendRedirect("http://localhost:8085/app/login");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out =  resp.getWriter();
        resp.setContentType("text/htmt");
        out.print("<h1>CANNOT POST</h1>");
    }
}
