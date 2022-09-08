package com.backend;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginBackend extends HttpServlet
{
	   protected  void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	    {
	    	  String email="",pass="";
	          email = req.getParameter("email");
	          pass = req.getParameter("password");
	          PrintWriter out = res.getWriter();
	         try
	          {
	        	Class.forName("com.mysql.jdbc.Driver");
	        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","poojapanchmani");
	        	Statement sm = conn.createStatement();
	        	try
	        	{
	            	ResultSet rt= sm.executeQuery("select * from userdata where email='" + email + "'");
	                rt.next();
	                String userExist = "",userpass="",usernum="",username="";
	                userExist = rt.getString("email").toString();
	                userpass = rt.getString("password").toString();
	                usernum = rt.getString("number").toString();
	                username = rt.getString("name").toString();
	                if(userpass.equals(pass))
	                {
		                HttpSession session = req.getSession();
		                SessionData userdata = new SessionData(username,userExist,userpass,usernum);
		                session.setAttribute("user",userdata);
		                RequestDispatcher rd=req.getRequestDispatcher("Home.html");
		                rd.include(req, res);
	                }
	                else
	                {
	                	out.println("<script type=\"text/javascript\">");
		                out.println("alert('Login Unsuccessfull');");
		                out.println("</script>");
		                RequestDispatcher rd=req.getRequestDispatcher("Register.html");
		                rd.include(req, res);
	                }
	        	}
	        	catch(Exception e)
	        	{
                	 out.println("<script type=\"text/javascript\">");
	        	     out.println("alert('Login Unsuccessfull');"); 
		             out.println("</script>");
		             RequestDispatcher rd=req.getRequestDispatcher("Register.html");
		             rd.include(req, res);
	        	}
			  } 
	          catch (Exception e) 
	         {
				e.printStackTrace();
				out.println(e);
			 }
	    }

}
