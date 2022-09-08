package com.backend;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Userdata extends HttpServlet 
{
	   protected  void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	    {
	    	  String name="",email="",number="",pass="",cpass="";
	          name = req.getParameter("name");
	          email = req.getParameter("email");
	          number = req.getParameter("number");
	          pass = req.getParameter("password");
	          cpass = req.getParameter("cpassword");
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
	                String userExist = "";
	                userExist = rt.getString("email");
               	    out.println("<script type=\"text/javascript\">");
	        	    out.println("alert('User Already Exist');"); 
		            out.println("</script>");
		            RequestDispatcher rd=req.getRequestDispatcher("Login.html");
		            rd.include(req, res);
	        	}
	        	catch(Exception e)
	        	{
	    		    String query = "insert into userdata(name,email,number,password,cpassword) values(?,?,?,?,?)";
	    	        PreparedStatement st = conn.prepareStatement(query);
	    	        st.setString(1,name);
	    	        st.setString(2,email);
	    	        st.setString(3,number);
	    	        st.setString(4,pass);
	    	        st.setString(5,cpass);
	    	        int count = 0;
	    	        count = st.executeUpdate(); 
               	    out.println("<script type=\"text/javascript\">");
	        	    out.println("alert('Registration Successfull');"); 
		            out.println("</script>");
		            RequestDispatcher rd=req.getRequestDispatcher("Login.html");
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
