package com.backend;

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
import java.util.Date;

public class ProductSetting extends HttpServlet 
{
	   protected  void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	   {
	          PrintWriter out = res.getWriter();
	          String name="Hello";
		         try
		          {
		        	Class.forName("com.mysql.jdbc.Driver");
		        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","poojapanchmani");
		        	Statement sm = conn.createStatement();
		        	try
		        	{
		            	ResultSet rt= sm.executeQuery("select * from ordertable");
		            	//rt.next();
		            	while(rt.next())
		            	{
		                    java.sql.Date currentDate= rt.getDate("productenddate");
		                    Date date = new Date();
		                    java.sql.Date futureDate= new java.sql.Date(date.getTime());
		                    int cf= (int) ((int) currentDate.getTime()-futureDate.getTime());
		                    out.println(currentDate);
		                    out.println(futureDate);
		                    out.println(cf);
		                    int productid=rt.getInt("productid");
		                    if(cf<=0)
		                    {
			                    String query = "update producttable set book="+false+" where productid="+productid+"";
					    	    PreparedStatement st = conn.prepareStatement(query);
			                    int count=st.executeUpdate();
					    	    query="delete from ordertable where productid="+productid+"";
					    	    st=conn.prepareStatement(query);
					    	    count=st.executeUpdate();
		                    }
		            	}
		        	}catch(Exception e)
		        	{
      	                 out.println(e.getMessage()); 
      	             }
              }
             catch(Exception e)
             {
      	          out.println(e.getMessage());
              }
              res.sendRedirect("Product");
	   }
}
