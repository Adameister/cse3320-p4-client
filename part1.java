/*
 *   Class: CSE 3330
 *   Semester: Fall 2016
 *   Student Name: Hudson, Adam, avh1758
 *   Student ID: 1000991758
*/

// THIS CODE IS PARTLY MY CODE
// I used the code given on blackboard and edited it to create this.

import java.sql.*;

public class part1 {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/avh1758"; //<-- replace your database name with xxx

   //  Database credentials
   static final String USER = "avh1758";	//<-- replace your username with xxx
   static final String PASS = "Goodday55";	//<-- replace your mysql password with xxx
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;

   boolean didItPrint = true;  // This boolean will check if the SQL statment was able to print out

   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 3.5: Ask the user for the sql statement requested
      System.out.print("Please answer the following questions: \n");
      // The following code will as the user for each of the requested criteria
      System.out.print("From Airport: ");
      String fromAirport = System.console().readLine();
      System.out.print("To Airport: ");
      String toAirport = System.console().readLine();
      System.out.print("Seat Class: ");
      String seatClass = System.console().readLine();

      //STEP 4: Execute a query
      System.out.println("Creating SQL statement...");
      stmt = conn.createStatement();
      String sql;

      // From STEP 3.5, the SQL statement will be formulated from the view
      // Airport_To_Airport_Capacity.
      sql = "SELECT * FROM Airport_To_Airport_Capacity " +
            "WHERE From_Airport = '" + fromAirport + "'" +
            "AND To_airport ='" + toAirport + "'" +
            "AND SeatClass ='" + seatClass + "'";
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
      	 System.out.print("Number of Available Seats is: ");
         System.out.println(rs.getString("AvailableSeats"));
         didItPrint = false;
      }
      
      // this if statement will be true if there was a problem printing the sql statement
      if( didItPrint ){
         System.out.print("Nothing was returned from the SQL statement.\n");
      }

      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
}//end FirstExample
