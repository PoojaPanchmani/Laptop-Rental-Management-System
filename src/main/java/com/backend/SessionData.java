package com.backend;

public class SessionData 
{
      String name,email,password,number;
      public SessionData(){}
      public SessionData(String name,String email,String password,String number)
      {
    	  this.name = name;
    	  this.email = email;
    	  this.number = number;
    	  this.password = password;
      }
      public String getName()
      {
    	  return name;
      }
      public String getEmail()
      {
    	  return email;
      }
      public String getPassword()
      {
    	  return password;
      }
      public String getNumber()
      {
    	  return number;
      }
}
