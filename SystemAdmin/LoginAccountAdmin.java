package fiveBtwoG.SystemAdmin;

import java.util.ArrayList;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;

import java.io.BufferedReader;

import javax.servlet.*;

import fiveBtwoG.entity.*;

public class LoginAccountAdmin extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException //must use name service
	{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String profileType = req.getParameter("profileType");
		
		
		if(Account.login(username, password, profileType)) {
			PrintWriter out = res.getWriter();
			out.println("Success");
			res.sendRedirect("loginRes.html");
		}else {
			PrintWriter out = res.getWriter();
			out.println("Fail");
		}
		
		
	}
}
