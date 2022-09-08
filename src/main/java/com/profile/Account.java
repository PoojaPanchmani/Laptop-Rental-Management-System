package com.profile;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.backend.SessionData;

public class Account extends HttpServlet
{
	      public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	     {
		       PrintWriter out = res.getWriter();
		       try
		       {
			       SessionData userdata = new SessionData();
			       HttpSession session =req.getSession();
			       if(session == null)
			    	    out.println("Hello");
			       else
			       {
				       userdata = (SessionData) session.getAttribute("user"); 
				       String name = userdata.getName().toString();
			    	   req.setAttribute("userdata",userdata);
			           RequestDispatcher rd=req.getRequestDispatcher("Accountend.jsp");
			           rd.include(req, res);         	   
			       }
		    	   
		       }
		       catch(Exception e)
		       {
               	    out.println("<script type=\"text/javascript\">");
	                out.println("alert('You are not login yet');");
	                out.println("</script>");
	                res.sendRedirect("Login.html");
		           // RequestDispatcher rd=req.getRequestDispatcher("Login.html");
		           // rd.include(req, res);      
		       } 
	     }
}
