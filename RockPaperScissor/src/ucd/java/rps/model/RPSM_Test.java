package ucd.java.rps.model;


import java.sql.SQLException;

import java.util.Scanner;


public class RPSM_Test extends RPSM_Connection{


	public static void main(String[] args) throws SQLException {

		
		  //getting names from user and saving those to the variables String
		
		  String p1name,p2name,p1Choice,p2Choice; Scanner sc = new Scanner(System.in);
		  System.out.println("Rock Paper Scissor - 2 Player Game\n");
		  System.out.println("Enter P1 name"); p1name =
		  sc.nextLine().trim().toString(); System.out.println("Enter P2 name"); p2name
		  = sc.nextLine().trim().toString();
		  
		  //getting user choices System.out.println("Enter Choices");
		  System.out.println(p1name+", enter your choice"); p1Choice =
		  sc.nextLine().trim().toString();
		  System.out.println(p2name+", enter your choice"); p2Choice =
		  sc.nextLine().trim().toString();
		  
		  
		  //passing saved inputs to the Player Detail class 
		  try { 
		  RPSM_PlayersDetail pdetails = new RPSM_PlayersDetail(p1name,p2name, p1Choice, p2Choice);
		  System.out.println("TESTING GAME | Choices ok.."); 
		  sc.close();
		  System.out.println(pdetails); 
		  } 
		  catch(IllegalArgumentException e) {  // TODO Auto-generated catch block
		  e.printStackTrace(); System.out.println("TESTING GAME | Incorrect Choices"); }
		  
		 
		 
		 
		  	//Db operations
			
			RPSM_DBOperations connection = new RPSM_DBOperations();
			connection.getGameRecords(connection.getConnection());
			//connection.deleteAllRecords(connection.getConnection());
			connection.getPlayerRecords(connection.getConnection());
	}
}
