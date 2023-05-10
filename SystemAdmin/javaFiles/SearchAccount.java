package fiveBtwoG.SystemAdmin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fiveBtwoG.entity.Account;

public class SearchAccount extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException //must use name service
	{
		String name = req.getParameter("name");
		
		Account returnedAcc = Account.searchAcc(name);
		if(returnedAcc != null) {
			PrintWriter out = res.getWriter();
			
			//res.sendRedirect("loginRes.html");
			 out.println(returnedAcc.toString());
		}else {
			PrintWriter out = res.getWriter();
			res.sendRedirect("searchAcc.html?status=fail");
		}
	}
}
