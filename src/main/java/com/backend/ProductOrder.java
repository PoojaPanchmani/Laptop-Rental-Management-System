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
import java.util.Calendar;
import java.util.Date;
import java.sql.*;


public class ProductOrder extends HttpServlet 
{
	   protected  void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	   {
	          PrintWriter out = res.getWriter();
	          HttpSession session = req.getSession();
              ProductBookCheck pdc = (ProductBookCheck) session.getAttribute("productinfo");
              String productname = pdc.getProductName();
              int productpriceper = pdc.getProductPrice();
              int productid = pdc.getProductId();
              String name = req.getParameter("firstname") + " " + req.getParameter("lastname");
              String email = req.getParameter("email");
              String number = req.getParameter("number");
              int days = Integer.parseInt(req.getParameter("days"));
              out.println(days);
              productpriceper= days*productpriceper;
              Date date = new Date();
              Calendar calendar = Calendar.getInstance();
              calendar.add(Calendar.DAY_OF_YEAR,days);
              Date fdate = calendar.getTime();
              java.sql.Date currentDate= new java.sql.Date(date.getTime());
              java.sql.Date futureDate= new java.sql.Date(fdate.getTime());
		         try
		          {
		        	Class.forName("com.mysql.jdbc.Driver");
		        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","poojapanchmani");
		        	Statement sm= conn.createStatement();  
		        	try
		        	{
	                    String query = "update producttable set book="+true+",days="+days+" where productid="+productid+"";
			    	    PreparedStatement st = conn.prepareStatement(query);
                        st.executeUpdate();
		        		//String query = "delete from ordertable where name='Panchmani Panchmani'"; 
		            	query = "insert into ordertable values(?,?,?,?,?,?,?,?)";
		    	        st = conn.prepareStatement(query);
		    	        st.setString(1,name);
		    	        st.setString(2,email);
		    	        st.setString(3,number);
		    	        st.setString(4,productname);
		    	        st.setInt(5,productpriceper);
		    	        st.setDate(6,currentDate);
		    	        st.setDate(7,futureDate);
		    	        st.setInt(8,productid);
		    	        st.executeUpdate();
		    	        session.setAttribute("personname",name);
		    	        session.setAttribute("totalprice",productpriceper);
		    	        session.setAttribute("productname",productname);
		    	        session.setAttribute("days",days);
			            RequestDispatcher rd=req.getRequestDispatcher("OrderBooked.html");
			            rd.include(req, res);
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
