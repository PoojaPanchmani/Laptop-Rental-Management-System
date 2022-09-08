package com.registereduser;

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
import java.sql.ResultSet;
import java.sql.Statement;

public class UserOrder extends HttpServlet 
{
    public void service(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
    {
    	PrintWriter out = res.getWriter();
        try
        {
      	     Class.forName("com.mysql.jdbc.Driver");
      	     Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","poojapanchmani");
      	     Statement sm = conn.createStatement();
      	     try
      	     {
          	      ResultSet rt= sm.executeQuery("select * from ordertable");
                  out.println("<html><head>");
                  out.println("<link href=\"css\\bootstrap.min.css\" rel=\"stylesheet\">\r\n"
                		  + "<script src='js\bootstrap.bundle.min.js'></script>");
                  out.println("</head><body>");
                  out.println("</body></html>");
                  out.println("<table class=\"table table-dark table-striped\">\r\n");
                  out.println("<thead>");
                  out.println("<tr>");
                  out.println("<th>Name</th>");
                  out.println("<th>Email</th>");
                  out.println("<th>Number</th>");
                  out.println("<th>Product<br>Name</th>");
                  out.println("<th>Product<br>StartDate</th>");
                  out.println("<th>Product<br>EndDate</th>");
                  out.println("<th>ProductPrice<br>(Per Day)</th>");
                  out.println("</tr>");
                  out.println("</thead>");
                  while(rt.next())
                  {
                	   out.println("<tbody>");
                	   out.println("<tr>");
                	   out.println("<td>");
                	   out.println(rt.getString("name").toString());
                	   out.println("</td>");
                	   out.println("<td>");
                	   out.println(rt.getString("email").toString());
                	   out.println("</td>");
                	   out.println("<td>");
                	   out.println(rt.getString("number").toString());
                	   out.println("</td>");
                	   out.println("<td>");
                	   out.println(rt.getString("productname").toString());
                	   out.println("</td>");
                	   out.println("<td>");
                	   out.println(rt.getString("productstartdate").toString());
                	   out.println("</td>");
                	   out.println("<td>");
                	   out.println(rt.getString("productenddate").toString());
                	   out.println("</td>");
                	   out.println("<td>");
                	   out.println(rt.getString("productprice").toString());
                	   out.println("</td>");
                	   out.println("</tr>");
                	   out.println("</tbody>");
                  }
      	     }
      	    catch(Exception e)
      	    {
            	out.println("<script type=\"text/javascript\">");
                out.println("alert('Data is Not Availble');");
                out.println("</script>");
                RequestDispatcher rd=req.getRequestDispatcher("Home.html");
                rd.include(req, res);  
      	    }
        }
        catch(Exception e)
        {
        	out.println("<script type=\"text/javascript\">");
            out.println("alert('Data is Not Availble');");
            out.println("</script>");
            RequestDispatcher rd=req.getRequestDispatcher("Home.html");
            rd.include(req, res);   	
        }
    }
}
