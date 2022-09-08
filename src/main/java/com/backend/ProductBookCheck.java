package com.backend;

public class ProductBookCheck
{
       String productname="";
       int productprice,productid;
       public ProductBookCheck() {}
       public ProductBookCheck(String productname,int productprice,int productid)
       {
    	   this.productname=productname;
    	   this.productprice=productprice;
    	   this.productid=productid;
       }
       public String getProductName()
       {
    	   return productname;
       }
       public int getProductPrice()
       {
    	   return productprice;
       }
       public int getProductId()
       {
    	   return productid;
       }
}
