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

public class BookProductBackend extends HttpServlet 
{
	   protected  void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	   {
	          PrintWriter out = res.getWriter();
	          int orderid= Integer.parseInt(req.getParameter("nameID"));
		         try
		          {
		        	Class.forName("com.mysql.jdbc.Driver");
		        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","poojapanchmani");
		        	Statement sm = conn.createStatement();
		        	HttpSession session = req.getSession();
		             
		        	try
		        	{
		            	ResultSet rt= sm.executeQuery("select * from producttable where productid="+orderid+"");
		                rt.next();
		                if(rt.getBoolean("book")==false)
		                {
                 			 ProductBookCheck pdc  = new ProductBookCheck(rt.getString("productname"),rt.getInt("productprice"),rt.getInt("productid"));
                			 session.setAttribute("productinfo",pdc);
                			 res.sendRedirect("OrderDetail.html");
		                }
		                else
		                {
		               	    out.println("<script type=\"text/javascript\">");
			        	    out.println("alert('Product Already Booked');"); 
				            out.println("</script>");
				            RequestDispatcher rd=req.getRequestDispatcher("Product");
				            rd.include(req, res);
		                }
		        	}
		        	catch(Exception e)
		        	{
		        		 out.println(e.getMessage());
		        	}
		          }
		         catch(Exception e)
		         {
		        	 out.println(e.getMessage()); 
		         }
	   }
	          
}
