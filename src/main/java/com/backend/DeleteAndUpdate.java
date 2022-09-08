package com.backend;

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
import java.sql.Statement;

public class DeleteAndUpdate extends HttpServlet 
{
	   protected  void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	   {
	          PrintWriter out = res.getWriter();
	         try
	          {
	        	Class.forName("com.mysql.jdbc.Driver");
	        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","poojapanchmani");
	        	Statement sm = conn.createStatement(); 
	        	try
	        	{
	        		//String query = "delete from ordertable where email='poojapanchmani0603@gmail.com'"; 
                    String query = "update producttable set book="+false+" where productid="+2+"";
		    	    PreparedStatement st = conn.prepareStatement(query);
                    int count=st.executeUpdate();
                    out.println(count);
	        	}
	        	catch(Exception e)
	        	{
	        		out.println(e);
	        	}
	          }
	          catch(Exception e)
	         {
	        	  out.println(e);
	         }
  
	   }
}
