package com.csvmodifer.util;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.csvmodifer.VerifyToken;

public class Util {
    public static boolean isAuthenticated(HttpServletRequest request) {
        Cookie user = Util.getCookie(request,"sessionid");
        if(user==null){
                
            return false;
        }
        return VerifyToken.verifyToken(user.getValue()); 
    }
    public static String getURL(HttpServletRequest request){
        System.out.println(request.getLocalPort());
            if(request.getLocalPort()==8085){
                return new String("http://localhost:8085/app/login");
            }
            return new String("http://localhost:8085/app");
            
    }
    public static Cookie getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
    public static Cookie getCookie(Cookie c ,String content){
        c.setValue(content);
        return c;
    }
    public static Cookie getCookie(Cookie c){
        return c;
    }
}
