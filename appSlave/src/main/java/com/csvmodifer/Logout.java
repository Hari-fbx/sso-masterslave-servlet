package com.csvmodifer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csvmodifer.util.Util;

public class Logout extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		Cookie cookie = Util.getCookie(request, "sessionid");

		if (cookie != null) {
			response.addCookie(Util.getCookie(cookie,"null"));
		}
		PrintWriter out = response.getWriter();
		System.out.println("logout get");
		// Cookie[] a = request.getCookies();
		// for(Cookie x : a){
		// 	System.out.println(x.getName()+"   "+x.getValue());
		// }
		out.print("You are successfully logged out!");
		response.sendRedirect("http://localhost:8085/app/logout");
		out.close();
	}
}
