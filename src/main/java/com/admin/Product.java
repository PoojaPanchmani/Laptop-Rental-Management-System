package com.admin;

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

public class Product extends HttpServlet
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
	        	HttpSession session = req.getSession();
	             
	        	try
	        	{
	        		ResultSet rt=sm.executeQuery("select * from producttable");
	            	rt.next();
            		out.println("<html><head>");
            		out.println("  <link href=\"Product.css\" rel=\"stylesheet\">\r\n"
            				+ "    <link href=\"css\\bootstrap.min.css\" rel=\"stylesheet\">\r\n"
            				+ "  <script src=\"js\\bootstrap.bundle.min.js\"></script> \r\n");
            		out.println("<script>");
            		out.println("function setSession(){");
            		out.println("alert('");
            		out.println(rt.getString("productname"));
            	    out.println("')");
            		out.println("}");
            		out.println("</script>");
            		out.println("</head><body>");
	            	while(rt.next())
	            	{
	            		String productimage=rt.getString("productimage");
	            		String productname=rt.getString("productname");
	            		int productid=rt.getInt("productid");
	            		int productrentalprice=rt.getInt("productprice");
	            		String productoriginalprice=rt.getString("productoriginalprice");
	            		int productID = rt.getInt("productid");
	            		Boolean book=rt.getBoolean("book");
	            		req.setAttribute("productname",productname);
	            		int days=rt.getInt("days");
	                    out.println("<div class='container mt-5'>");
	                    out.println("<div class='product_b' style='width:50%;'>");
	                    out.println("<div class='d-flex justify-content-between'>");
	                    out.println("<div>");
	                    out.println("<img src='");
	                    out.println(productimage);
	                    out.println(" alt='Laptop' style='width:240px;height:200px;margin-left:20%;>");
	                    out.println("</div>");
	                    out.println("<div class='product_detail_b'>");
	                    out.println("<h5 class='product_name_b'> name='name' style='font-size:20px;margin-left:20px;color:black;font-weight:bold;'");
	                    out.println(productname);
	                    out.println("</h5><br>");
	                    out.println("<p style='margin-top:10px;margin-left:20px;color:black;font-weight:bold;'>");
	                    if(book == true)
	                    	out.print("Not Available Already Booked");
	                    else 
	                    	out.println("Avaible");
	                    out.println("</p>");
	                    out.println("<div class='d-flex justify-content-between'>");
	                    out.println("<p style='margin-top:10px;margin-left:20px;color:black;font-weight:bold;'>");
	                    out.println("Rent Price");
	                    out.println("</p>");
	                    out.println("<p style='margin-top:10px;margin-left:20px;color:black;font-weight:bold;margin-right:20px;'>");
	                    out.println(productrentalprice);
	                    out.println("</p>");
	                    out.println("</div>");
	                    out.println("<p style='margin-top:10px;margin-left:20px;color:black;font-weight:bold;'>");
	                    out.println(productoriginalprice);
	                    out.println("</p>");
	                    out.println("<div>");
	                    out.println("<a href='/LaptopProject/bookproductbackend?nameID="+productID +"'><button type='submit' class='buy_cart_b'");
	                    out.println(" style='margin-bottom:10px;'>Book Now</button></a>");
	                    out.println("</div></div>");
	                    out.println("</div>");
		            	out.println("</div>");
		            	out.println("</div>");
	            	}
                    out.println("</body></html>");
                    session.setAttribute("name",name);
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
