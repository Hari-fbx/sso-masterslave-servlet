package com.csvmodifer;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.csvmodifer.accounts.Users;
import com.csvmodifer.util.RSAUtil;
import com.csvmodifer.util.Util;  
public class Login extends HttpServlet {  
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                    throws ServletException, IOException {
        PrintWriter out=response.getWriter(); 
        System.out.println("login");
        System.out.println("login post");
        System.out.println(request.getRequestURI());
        response.setContentType("text/html");
        // out.println("port:"+request.getParameter("port"));
        if(Util.isAuthenticated(request)){
            response.sendRedirect(Util.getURL(request));
        }else{
            String name=request.getParameter("name");
            String password=request.getParameter("password");  
            if(!Users.user.containsKey(name)){
                out.println("Sorry, user not found");  
                out.println("<html><body>login<form action='login' method='post'>Name:<input type='text' name='name'><br>Password:<input type='password' name='password'><br><input type='submit' value='login'></form><br><a href='logout'>logout</a></body></html>");
            }else{ 
                // System.out.println(data.toString());
                if(password.equals(Users.user.get(name))){  
                    byte[] sessionid= null;
                    try {
                        sessionid = RSAUtil.encrypt(new String(name), RSAUtil.publicKey);
                    } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException
                            | NoSuchPaddingException | NoSuchAlgorithmException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    String enc =Base64.getEncoder().encodeToString(sessionid);
                    Cookie session = new Cookie("sessionid",new String(enc));
                
                    System.out.println(enc);
                    try {
                        System.out.println(RSAUtil.decrypt(enc, RSAUtil.privateKey));
                    } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
                            | NoSuchAlgorithmException | NoSuchPaddingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                    response.addCookie(Util.getCookie(session));
                    out.println("logged in as "+name );
                    out.println();
                    out.println("<a href='logout'>logout</a><br>");

                }  
                else{  
                    out.print("Sorry, username or password error!");  
                    request.getRequestDispatcher("index.html").include(request, response);  
                }  
            }
        }   
        out.close(); 
        
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login get");
        PrintWriter r = resp.getWriter();
        resp.setContentType("text/html");
        if(Util.isAuthenticated(req)){
            resp.sendRedirect(Util.getURL(req));
 
        }else{
            
            r.println("<html><body>login<form action='login' method='post'>Name:<input type='text' name='name'><br>Password:<input type='password' name='password'><br><input type='submit' value='login'></form><br><a href='logout'>logout</a></body></html>");
        }
        // req.getRequestDispatcher("index.html").forward(req, resp); 
        
    }
}  
