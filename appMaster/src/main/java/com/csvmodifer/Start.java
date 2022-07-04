package com.csvmodifer;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.csvmodifer.util.RSAUtil;
import com.csvmodifer.util.Util;

public class Start extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");  
        System.out.println("start get");
        PrintWriter out=resp.getWriter();
        if(Util.isAuthenticated(req)){
            Cookie user = Util.getCookie(req,"sessionid");
            String name =null;
            try {
                name = RSAUtil.decrypt(user.getValue(),RSAUtil.privateKey);
            } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
                    | NoSuchPaddingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            out.println("logged in as "+name );
            out.println();
            out.println("<a href='logout'>logout</a><br>");
        }else{
            System.out.println("why");
            resp.sendRedirect("http://localhost:8085/app/login");

        }
        
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        System.out.println("start post");
        if(Util.isAuthenticated(req)){
            out.println("welcome "+req.getSession().getAttribute("name"));
            out.println("<a href='logout'>logout</a><br>");
        }else{
            resp.sendRedirect("http://localhost:8085/app/login");
        }
        // out.println("CANNOT POST");
    }
}

