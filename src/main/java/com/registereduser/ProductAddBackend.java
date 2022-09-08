package com.registereduser;

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

import com.backend.SessionData;

public class ProductAddBackend extends HttpServlet 
{
	   protected  void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	    {
	    	  String productname="",productimage="",productoriginalprice="";
	    	  int productrentalprice;
	          productname=req.getParameter("productname");
	          productimage=req.getParameter("productimage");
	          productoriginalprice=req.getParameter("productoriginalprice");
	          productrentalprice=Integer.parseInt(req.getParameter("productrentalprice"));
	          PrintWriter out = res.getWriter();
	         try
	          {
	        	Class.forName("com.mysql.jdbc.Driver");
	        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","poojapanchmani");
	        	Statement sm = conn.createStatement();
	        	try
	        	{
	        		ResultSet rt =sm.executeQuery("select * from producttable order by productid desc limit 1");
	        		rt.next();
	        	    int count = rt.getInt("productid");
	        	    String query="insert into producttable values(?,?,?,?,?,?,?)";
	    	        PreparedStatement st = conn.prepareStatement(query);
	    	        st.setInt(1,count+1);
	    	        st.setString(2,productimage);
	    	        st.setString(3,productname);
	    	        st.setInt(4,productrentalprice);
	    	        st.setInt(5,0);
	    	        st.setBoolean(6,false);
	    	        st.setString(7,productoriginalprice);
	    	        int val=st.executeUpdate();
	        	}
	        	catch(Exception e)
	        	{
               	    out.println("<script type=\"text/javascript\">");
	        	    out.println("alert('Product Add UnSuccessfull');"); 
		            out.println("</script>");
		            RequestDispatcher rd=req.getRequestDispatcher("Home.html");
		            rd.include(req,res);
	        	}
			  } 
	          catch (Exception e) 
	         {
             	    out.println("<script type=\"text/javascript\">");
	        	    out.println("alert('Product Add UnSuccessfull');"); 
		            out.println("</script>");
		            RequestDispatcher rd=req.getRequestDispatcher("Home.html");
		            rd.include(req,res);
			 }
	    }   
}
