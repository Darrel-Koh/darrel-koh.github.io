package fiveBtwoG.SystemAdmin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fiveBtwoG.entity.Account;
import fiveBtwoG.entity.Profile;

public class CreateProfile extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException //must use name service
	{
		String profID = req.getParameter("accID");
		String name = req.getParameter("name");
		String status =  req.getParameter("status");
		
		boolean Success = Profile.createProfile(profID, name, status);
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