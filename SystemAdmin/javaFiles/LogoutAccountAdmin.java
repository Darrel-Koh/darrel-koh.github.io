package fiveBtwoG.SystemAdmin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fiveBtwoG.entity.Account;

public class LogoutAccountAdmin extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException //must use name service
	{
		String accID = req.getParameter("accID");
		
		if(Account.logout(accID)) {
			PrintWriter out = res.getWriter();
			out.println("Success");
			res.sendRedirect("test.html");
		}else {
			PrintWriter out = res.getWriter();
			out.println("Fail");
		}
		
		
	}
}
