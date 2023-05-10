package fiveBtwoG.SystemAdmin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fiveBtwoG.entity.Profile;

public class SuspendProfile extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException //must use name service
	{
		String profID = req.getParameter("profID");
		
		boolean Success = Profile.suspendProfile(profID);
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
