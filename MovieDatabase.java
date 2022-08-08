/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mulsoft;

/**
 *
 * @author kumaraswamy
 */
import java.util.*;
import java.lang.*;
import java.sql.*;
import java.io.*;

public class MovieDatabase 
{
  public void getDetails()
 {
  try
  {

  //register the driver using mySql here
  Class.forName("com.mysql.cj.jdbc.Driver");
	
  //create a connection to the database 'db1'
  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesDb","root","Tej@1404906");
  
  //Create a prepare statement interface
  PreparedStatement ps=con.prepareStatement("select * from Movies;");
  
  //storing the result into a result set and displaying it
  ResultSet rs=ps.executeQuery();
  
  //getting metadata
  ResultSetMetaData rsmd=rs.getMetaData();
  
  //printing column names
  for(int i=1;i<=rsmd.getColumnCount();i++)
  {
    System.out.println(rsmd.getColumnName(i)+"\t");
  }
 
  //printing values present in result set
  while(rs.next())
  {
	System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getInt(5));
  }
 }
 catch(Exception e)
 {
   System.out.println(e.getMessage());
   e.printStackTrace();
 }
//  finally
//  {
//    //close the connection
//    con.close();
//  }

}
public boolean insertValues(String name,String actor,String actress,String director,int year){
    boolean res;
    try{
         //register the driver using mySql here
  Class.forName("com.mysql.cj.jdbc.Driver");
	
  //create a connection to the database 'db1'
  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesDb","root","Tej@1404906");
  
  //Create a prepare statement interface
  PreparedStatement ps=con.prepareStatement("insert into Movies values(?,?,?,?,?);");
  
  //storing the result into a result set and displaying it
  ps.setString(1,name);
  ps.setString(2,actor);
  ps.setString(3,actress);
  ps.setString(4,director);
  ps.setInt(5,year);
  int rs=ps.executeUpdate();
  if(rs>0){
      res= true;
  }
  else{
      res= false;
  }
   }
  catch(Exception e){
        System.out.println(e.getMessage());
        e.printStackTrace();
        res=false;
   }
    return res;
}
public void createTable(){
    try
  {

  //register the driver using mySql here
  Class.forName("com.mysql.cj.jdbc.Driver");
	
  //create a connection to the database 'db1'
  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesDb","root","Tej@1404906");
  
  //Create a prepare statement interface
  String createQuery="create table movies_new(movie_name varchar(20),actor varchar(20),actress varchar(20),director varchar(20),year_of_release numeric(4));";
  PreparedStatement ps=con.prepareStatement(createQuery);
  
  //storing the result into a result set and displaying it
  int rs=ps.executeUpdate();
  if(rs>=0){
      System.out.print("table created");
  }
  else{
      System.out.print("table creation failed");
  }

  
 }
 catch(Exception e)
 {
   System.out.println(e.getMessage());
   e.printStackTrace();
 }
}
public void createDatabase(){
      try
  {

  //register the driver using mySql here
  Class.forName("com.mysql.cj.jdbc.Driver");
	
  //create a connection to the database 'db1'
  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","Tej@1404906");
  
  //Create a prepare statement interface
  String createQuery="create database moviesDb;";
  PreparedStatement ps=con.prepareStatement(createQuery);
  
  //storing the result into a result set and displaying it
  int rs=ps.executeUpdate();
  if(rs>=0){
      System.out.print("database created");
  }
  else{
      System.out.print("database creation failed");
  }

  
 }
 catch(Exception e)
 {
   System.out.println(e.getMessage());
   e.printStackTrace();
 }
    
}
public static void main(String[] args) {
    MovieDatabase moviesDB = new MovieDatabase();
    //Database creation
    moviesDB.createDatabase();
    //table creation
    moviesDB.createTable();
    //inserting values into table
    if(moviesDB.insertValues("jalsa", "pavan", "boomika", "trivikram",2011)){
        System.out.print("dsadasf");
    }
    else{
        System.out.print("fail");
    }
    //retrieving data from table
    moviesDB.getDetails();
}

}

    