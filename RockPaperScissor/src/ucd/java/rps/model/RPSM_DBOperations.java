package ucd.java.rps.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This model extends the connection class and is created update/insert/delete/retrieve 
 * players and game records
 * @author akshaythakare
 *
 */

public class RPSM_DBOperations extends RPSM_Connection {


	public RPSM_DBOperations()
	{
		System.out.println("**Entered in DBOperations Model**");
	}
	
	/**
	 * Method is defined to execute DDL queries like to create table
	 */
	public void createTable()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection conn = DriverManager.getConnection(connectionString,username,password);
			System.out.println("Connected to DB "+getConnection().getMetaData());
			
			Statement stmt = getConnection().createStatement();
			//int success = stmt.executeUpdate("ALTER TABLE GAME_RECORDS ADD MATCH_DATE DATETIME");
			int success = stmt.executeUpdate("CREATE TABLE PLAYER_RECORDS (PLAYER_NAME varchar(255) NOT NULL,GAME_WINS int,GAME_LOSE int,GAME_TIE int,PRIMARY KEY (PLAYER_NAME));");
			
			System.out.println(success == 0 ? "Table created successfully":"Error occurred");
			getConnection().close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection error..");
			e.printStackTrace();
		}
	}
	
	/**
	 * This method insert game records as per given date to table GAME_RECORDS
	 * @param PlayerOne Player One Name
	 * @param PlayerTwo Player Two Name
	 * @param Winner Winner of the Game
	 * @throws SQLException Exception if any syntax/anything went wrong
	 */
	public void insertGameRecords(String PlayerOne, String PlayerTwo, String Winner) throws SQLException
	{
		java.util.Date dt = new java.util.Date(System.currentTimeMillis());

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);
		Connection con = getConnection();
		System.out.println("Model | DBOperations |insertGameRecords "+currentTime+" "+PlayerOne+" "+PlayerTwo+" "+Winner+" "+con);
		
		
		  Statement stmt = con.createStatement(); 
		  String query = "INSERT INTO GAME_RECORDS (Match_Date, PlayerOne, PlayerTwo, Winner) values ('"+currentTime+"','"+PlayerOne+"','"+PlayerTwo+"','"+Winner+"');";
		  PreparedStatement ps = con.prepareStatement(query);
		  ps.execute();
		  stmt.executeQuery("COMMIT;");
		  System.out.println("Model | DBOperations |insertGameRecords | Record Inserted ");
		  stmt.close();
		  con.close();
		 
		
		//ResultSet rs = stmt.executeQuery("SELECT * FROM GAME_RECORDS;");
	}
	
	/**
	 * This method is created to insert records of player to PLAYER_RECORDS
	 * @param playerName Player name p
	 * @param wins Total wins of player p
	 * @param lose Total lose of player p
	 * @param tie total tie of player p
	 * @throws SQLException SQLException Exception if any syntax/anything went wrong
	 */
	public void insertPlayerRecords(String playerName, int wins, int lose, int tie) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		int totalWins,totalLose,totalTies;
		Statement stmt = con.createStatement();
		System.out.println("Model | DBOperations | insertPlayerRecords P- "+playerName+" wins-"+wins+" lose-"+lose+" tie-"+tie);
		ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER_RECORDS WHERE PLAYER_NAME ='"+playerName+"';");
		
		if(rs.next() == false)
		{
			System.out.println("Model | DBOperations | insertPlayerRecords "+playerName+" is new player");
			totalWins = wins;
			totalLose = lose;
			totalTies = tie;
			String query = "INSERT INTO PLAYER_RECORDS (PLAYER_NAME, GAME_WINS, GAME_LOSE, GAME_TIE) values ('"+playerName+"','"+totalWins+"','"+totalLose+"','"+totalTies+"');";
			PreparedStatement ps = con.prepareStatement(query);
			ps.execute();
			stmt.executeQuery("COMMIT;");
			System.out.println("Model | DBOperations | insertPlayerRecords "+playerName+" updated");
			
		}
		else
		{
			System.out.println("Model | DBOperations | insertPlayerRecords "+playerName+" is existing player");
			totalWins = rs.getInt("GAME_WINS") + wins;
			totalLose = rs.getInt("GAME_LOSE") + lose;
			totalTies = rs.getInt("GAME_TIE") + tie;
			
			String query = "UPDATE PLAYER_RECORDS SET GAME_WINS = ?, GAME_LOSE = ?, GAME_TIE = ? WHERE PLAYER_NAME = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, totalWins);
			ps.setInt(2, totalLose);
			ps.setInt(3, totalTies);
			ps.setString(4, playerName);
			int rows = ps.executeUpdate();
			System.out.println("Model | DBOperations | insertPlayerRecords - Rows updated "+rows);
			System.out.println("Model | DBOperations | insertPlayerRecords "+playerName+" "+totalWins+" "+totalLose+" "+totalTies);
		}
		
		
		stmt.close();
		con.close();
	}

	/**
	 * This method is designed to get the game records of all the play
	 * @param con Connection
	 * @throws SQLException SQLException Exception if any syntax/anything went wrong
	 */
	public void getGameRecords(Connection con) throws SQLException {
		System.out.println("Model | DBOperations | getGameRecords");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM GAME_RECORDS");
		//ResultSet rs2 = stmt.executeQuery("SELECT * FROM PLAYER_RECORDS");
		
		System.out.println("**Game Records**");
		System.out.print("GameId"+"\t"+"P1"+"\t"+"P2"+"\t"+"Winner"+"\t"+"Date"+"\n");
		while(rs.next())
		{
			
			System.out.print(rs.getInt(1));
			System.out.print("\t"+rs.getString(2));
			System.out.print("\t"+rs.getString(3));
			System.out.print("\t"+rs.getString(4));
			System.out.print("\t"+rs.getString(5));
			System.out.println();
		}
		
		stmt.close();
		con.close();
	}
	
	/**
	 * This method is created to get individual player records
	 * @param con Connection
	 * @throws SQLException SQLException Exception if any syntax/anything went wrong
	 */
	public void getPlayerRecords(Connection con) throws SQLException {
		System.out.println("Model | DBOperations | getPlayerRecords");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER_RECORDS");

		System.out.println("**Player Records**");
		System.out.print("Player Name"+"\t"+"Wins"+"\t"+"Lose"+"\t"+"Ties"+"\n");
		while(rs.next()) { System.out.print(rs.getString(1));
		System.out.print("\t\t"+rs.getInt(2)); System.out.print("\t"+rs.getInt(3));
		System.out.print("\t"+rs.getInt(4));
		System.out.println();}
		stmt.close();
		con.close();
	}

	/**
	 * This method is used to truncate the table
	 * @param conn connection
	 * @throws SQLException SQLException Exception if any syntax/anything went wrong
	 */
	public void deleteAllRecords(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Model | DBOperations | deleteAllRecords");
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("TRUNCATE TABLE GAME_RECORDS");
		stmt.executeUpdate("COMMIT;");
		//stmt.executeUpdate("ALTER TABLE GAME_RECORDS AUTO_INCREMENT = 0");

		System.out.println("Model | DBOperations | deleteAllRecords | All rows deleted ");
		
		stmt.close();
		conn.close();
		
	}

	
	
}
