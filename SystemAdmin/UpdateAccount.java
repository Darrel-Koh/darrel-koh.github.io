package fiveBtwoG.SystemAdmin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fiveBtwoG.entity.Account;

public class UpdateAccount extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException //must use name service
	{
		String accID = req.getParameter("accID");
		String name = req.getParameter("name");
		String type = req.getParameter("type");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String status = req.getParameter("status");
		
		boolean Success = Account.updateAccount(accID, name, type, username, password, status);
		if(Success) {
		PrintWriter out = res.getWriter();
			out.println("Sucess");
			res.sendRedirect("loginRes.html");
		}else {
			PrintWriter out = res.getWriter();
			out.println("Fail");
		}	
	}
}
