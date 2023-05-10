package fiveBtwoG.SystemAdmin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fiveBtwoG.entity.Account;

public class CreateAccount extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException //must use name service
	{
		String accID = req.getParameter("accID");
		String name = req.getParameter("name");
		String type = req.getParameter("type");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		boolean Success = Account.createAccount(accID, name, type, username, password);
		if(Success) {
			PrintWriter out = res.getWriter();
			out.println("Success");
			res.sendRedirect("loginRes.html");
		}else {
			PrintWriter out = res.getWriter();
			out.println("Fail");
		}
	}
}

