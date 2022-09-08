package com.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminBackend extends HttpServlet
{
        public void service(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
        {
        	PrintWriter out= res.getWriter();
            try
            {
            	String email = req.getParameter("email");
            	String pass = req.getParameter("password");
            	if(email.equals("poojaadminpanchmani@gmail.com"))
            	{
            		 if(pass.equals("poojaadmin"))
            		 {
            			  RequestDispatcher rd  =req.getRequestDispatcher("AdminProfile.html");
    		              rd.include(req, res);
            		 }
            		 else
            		 {
    	                	out.println("<script type=\"text/javascript\">");
    		                out.println("alert('Unsuccessfull Login');");
    		                out.println("</script>");
    		                RequestDispatcher rd=req.getRequestDispatcher("Home.html");
    		                rd.include(req, res); 
            		 }
            	}
            	else
            	{
                	out.println("<script type=\"text/javascript\">");
                    out.println("alert('Unsuccessfull Login');");
                    out.println("</script>");
                    RequestDispatcher rd=req.getRequestDispatcher("Home.html");
                    rd.include(req, res); 
            	}
            }
            catch(Exception e)
            {
            	out.println("<script type=\"text/javascript\">");
                out.println("alert('Unsuccessfull Login');");
                out.println("</script>");
                RequestDispatcher rd=req.getRequestDispatcher("Home.html");
                rd.include(req, res); 
            }
        }
}
