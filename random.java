import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import javax.swing.*;
public class random
{
	public int genrandom()
	 {
	Random rdm = new Random();
	
	int no =1000000+rdm.nextInt(100000);
	return no;
 }
 public Connection connect()
 {
	Connection con=null; 
	try{
	Class.forName("com.mysql.jdbc.Driver");
	//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase0","root","");
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadatabase0","root","");	
	}
	 catch(Exception e)
	 {
		 System.out.println(e);
	 }
	return con;
 }
 public void createTable(int num,Connection con){
	try{
	String q ="create table `"+num+"` (ProductID int,ProductName varchar(100),ProductPrice varchar(50),MSP varchar(50),Description varchar(200),Image blob,Available varchar(50),PRIMARY KEY(ProductID))";	
	PreparedStatement pstmt = con.prepareStatement(q);
	pstmt.executeUpdate();
	
	}
	catch(Exception e)
	{
		System.out.println(e);
		
	}
 }
 public static void main(String args[])
 {
	random r = new random();
	int res =r.genrandom();
    Connection con= r.connect();
	r.createTable(res,con);
	System.out.println(res);
 }
}

















