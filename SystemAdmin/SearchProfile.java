package fiveBtwoG.SystemAdmin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fiveBtwoG.entity.Profile;

public class SearchProfile extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException //must use name service
	{
		String name = req.getParameter("name");
		
		Profile returnedProf = Profile.searchProfile(name);
		if(returnedProf != null) {
			PrintWriter out = res.getWriter();
			out.println(returnedProf.toString());
			//res.sendRedirect("loginRes.html");
		}else {
			PrintWriter out = res.getWriter();
			out.println("Fail");
		}
	}
}
