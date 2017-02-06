/*
 *   Class: CSE 3330
 *   Semester: Fall 2016
 *   Student Name: Hudson, Adam, avh1758
 *   Student ID: 1000991758
*/

// THIS CODE IS PARTLY MY CODE
// I used the code given on blackboard and edited it to create this.

import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class part2 {
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
   boolean stayInLoop = true;  // This boolean will check for valid input from the user
   int i = 0;                  // Counter varable i
   String quit = "/Q";
   boolean didQuit = false;
   int passengerID = 1;        // this varable will find the passenger ID

   // the folowing varables will be recived from the user
   String passengerName = "";
   String passengerPhone = "";
   String flightNum = "";
   String flightDate = "";
   String fromAirport = "";
   String toAirport = "";
   String seatClass = "";
   String dateBooked = "";

   // the date booked is the current date noted by the system
   DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
   Date dateobj = new Date();
   dateBooked = (df.format(dateobj));

   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 3.5: Ask the user for the sql statement requested
      System.out.print("Please answer the following questions: \n");

      i = 0;
      while( i < 1 ){
         
         // each set of data will be in a while loop to keep the user from entering bad data
      	 // the user will only be able to break out of the loop if they enter the correct data or quit the program
         while( stayInLoop ){
            // The following code will as the user for each of the requested criteria
            System.out.print("Passenger Name: ");
            passengerName = System.console().readLine();
      
            // these if statements will listen for input from the user.
            // If the user wants to quit, the program will break out of the loop
            if( quit.equals( passengerName ) ){
               didQuit = true;
               break;
            }
            // stayInLoop will keep the user from exiting with bad data
            stayInLoop = false;
            // if the data is bad, stayInLoop will be true
            if( passengerName.isEmpty() ){
               System.out.println("Sorry, you must enter a name.");
               stayInLoop = true;
            }
            if( passengerName.matches(".*\\d.*") ){
               System.out.println("Sorry, you must enter a valid name.");
               stayInLoop = true;
            }
         }

         // didQuit will list for the quit command given by the user.
         // if the user has quit, this if statement will break them out of the sql creation
         if( didQuit ){
            break;
         }

         stayInLoop = true;
         while( stayInLoop ){
            // The following code will as the user for each of the requested criteria
            System.out.print("Passenger Phone: ");
            passengerPhone = System.console().readLine();
      
            // these if statements will listen for input from the user.
            // If the user wants to quit, the program will break out of the loop
            if( quit.equals( passengerPhone ) ){
               didQuit = true;
               break;
            }
            stayInLoop = false;
            // this set of code was made obsolete by the following if statement 
            /*if( passengerPhone.length() != 10 ){
               System.out.println("Sorry, you must enter a valid Phone Number.");
               stayInLoop = true;
            }*/
            if( !(passengerPhone.matches("([0-9]{10})")) ){
               System.out.println("Sorry, you must enter a valid Phone Number.");
               stayInLoop = true;
            }
         }

         if( didQuit ){
            break;
         }

         stayInLoop = true;
         while( stayInLoop ){
            // The following code will as the user for each of the requested criteria
            System.out.print("Flight Number: ");
            flightNum = System.console().readLine();
      
            // these if statements will listen for input from the user.
            // If the user wants to quit, the program will break out of the loop
            if( quit.equals( flightNum ) ){
               didQuit = true;
               break;
            }
            stayInLoop = false;

            // the following code was made obsolete by the next if statement
            /*if( flightNum.isEmpty() ){
               System.out.println("Sorry, you must enter a Flight Number.");
               stayInLoop = true;
            }*/
            if( !(flightNum.matches("100") || flightNum.matches("200") || flightNum.matches("300") || flightNum.matches("400") || flightNum.matches("500") || flightNum.matches("600") || flightNum.matches("700") ) ){
               System.out.println("Sorry, you must enter a valid Flight Number.");
               stayInLoop = true;
            }
         }

         if( didQuit ){
            break;
         }

         stayInLoop = true;
         while( stayInLoop ){
            // The following code will as the user for each of the requested criteria
            System.out.print("Flight Date: ");
            flightDate = System.console().readLine();
      
            // these if statements will listen for input from the user.
            // If the user wants to quit, the program will break out of the loop
            if( quit.equals( flightDate ) ){
               didQuit = true;
               break;
            }
            stayInLoop = false;
            if( flightDate.isEmpty() ){
               System.out.println("Sorry, you must enter a Flight Date.");
               stayInLoop = true;
            }
            // this will check to see if the flight date is in the correct format
            /*if( !(flightDate.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) ){
               System.out.println("Sorry, you must enter a valid Flight Date.");
               stayInLoop = true;
            }*/

            // there is only two dates for flights in the database
            // the user must enter one of these two flight dates for the statement to work
            if( !(flightDate.matches("2015-10-06") || flightDate.matches("2015-10-08")) ){
               System.out.println("Sorry, you must enter a valid Flight Date.");
               stayInLoop = true;
            }
         }

         if( didQuit ){
            break;
         }

         stayInLoop = true;
         while( stayInLoop ){
            // The following code will as the user for each of the requested criteria
            System.out.print("From Airport: ");
            fromAirport = System.console().readLine();
      
            // these if statements will listen for input from the user.
            // If the user wants to quit, the program will break out of the loop
            if( quit.equals( fromAirport ) ){
               didQuit = true;
               break;
            }
            stayInLoop = false;
            /*if( fromAirport.isEmpty() ){
               System.out.println("Sorry, you must enter an Airport.");
               stayInLoop = true;
            }*/
            if( !(fromAirport.matches("BWI") || fromAirport.matches("DFW") || fromAirport.matches("FLL") || fromAirport.matches("IAH") || fromAirport.matches("JFK") || fromAirport.matches("LAX") || fromAirport.matches("LGA") || fromAirport.matches("ORD") || fromAirport.matches("SFO") ) ){
               System.out.println("Sorry, you must enter a valid Airport.");
               stayInLoop = true;
            }
         }

         if( didQuit ){
            break;
         }

         stayInLoop = true;
         while( stayInLoop ){
            // The following code will as the user for each of the requested criteria
            System.out.print("To Airport: ");
            toAirport = System.console().readLine();
      
            // these if statements will listen for input from the user.
            // If the user wants to quit, the program will break out of the loop
            if( quit.equals( toAirport ) ){
               didQuit = true;
               break;
            }
            stayInLoop = false;
            /*if( toAirport.isEmpty() ){
               System.out.println("Sorry, you must enter an Airport.");
               stayInLoop = true;
            }*/
            if( !(toAirport.matches("BWI") || toAirport.matches("DFW") || toAirport.matches("FLL") || toAirport.matches("IAH") || toAirport.matches("JFK") || toAirport.matches("LAX") || toAirport.matches("LGA") || toAirport.matches("ORD") || toAirport.matches("SFO") ) ){
               System.out.println("Sorry, you must enter a valid Airport.");
               stayInLoop = true;
            }
         }

         if( didQuit ){
            break;
         }

         stayInLoop = true;
         while( stayInLoop ){
            // The following code will as the user for each of the requested criteria
            System.out.print("Seat Class: ");
            seatClass = System.console().readLine();
      
            // these if statements will listen for input from the user.
            // If the user wants to quit, the program will break out of the loop
            if( quit.equals( seatClass ) ){
               didQuit = true;
               break;
            }
            stayInLoop = false;
            /*if( seatClass.isEmpty() ){
               System.out.println("Sorry, you must enter a Seat Class.");
               stayInLoop = true;
            }*/
            if( !(seatClass.matches("E") || seatClass.matches("F")) ){
               System.out.println("Sorry, you must enter a valid Seat Class.");
               stayInLoop = true;
            }
         }

         if( didQuit ){
            break;
         }

         //STEP 4: Execute a query
         System.out.println("Creating SQL statement...");
         stmt = conn.createStatement();
         String sql;

         // From STEP 3.5, the SQL statement will be formulated from the view
         // Check if passenger exists
         sql = "SELECT * FROM Passenger " +
               "WHERE '" + passengerName + "' = name " +
               "AND '" + passengerPhone + "'";
         ResultSet rs = stmt.executeQuery(sql);

         //STEP 5: Extract data from result set
         while(rs.next()){
            //Retrieve by column name
            // this while loop will only execute if there was data in the result
            // meaning, there was a passenger
            // this will grab the passenger ID and tell the
            // next if statement to do not create a new passenger
            passengerID = Integer.parseInt(rs.getString("ID"));
            didItPrint = false;
         }
      
         // this if statement will be true if there was no matching passenger
         if( didItPrint ){
            sql = "SELECT * FROM Passenger";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
               // this set of code will prob the passenger table to find the next passenger ID needed
               rs.getString("ID");
               passengerID++;
               //System.out.println(passengerID);
            }
            // create the non-existing passenger
            sql = "INSERT INTO Passenger " +
                  "VALUES (" + passengerID + "," +
                  "'" + passengerName + "'," +
                  "'" + passengerPhone + "')";
            System.out.println(sql);
            // this will update the database with the new passenger
            stmt.executeUpdate(sql);
         }
         // END OF THE PASSENGER SQL STATEMENT

         // START RESERVATION SQL STATEMENT
         // this will update the database with the new reservation
         sql = "INSERT INTO Reservation " +
               "VALUES (" + passengerID + "," +
               "'" + flightNum + "'," +
               "'" + flightDate + "'," +
               "'" + fromAirport + "'," +
               "'" + toAirport + "'," +
               "'" + seatClass + "'," +
               "'" + dateBooked + "'," +
               "NULL)";
         stmt.executeUpdate(sql);

         //STEP 6: Clean-up environment
         rs.close();
         stmt.close();
         conn.close();

         // Incriment the counter
         i = i + 1;
      }
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
