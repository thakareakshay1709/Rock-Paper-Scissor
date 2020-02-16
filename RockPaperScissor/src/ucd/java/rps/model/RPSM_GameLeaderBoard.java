package ucd.java.rps.model;

import java.sql.SQLException;

/**
 * This model is created set the score of players according to wins/loss/ties
 * @author akshaythakare
 *
 */
public class RPSM_GameLeaderBoard extends RPSM_Connection{

	public RPSM_GameLeaderBoard()
	{
		System.out.println("****Entered in Model - GameLeaderBoard****");
	}
	/**
	 * This method is designed to update the game records
	 * @param player1Name PlayerOne Name
	 * @param player2Name PlayerTwo Name
	 * @param Winner Winner 
	 */
	public void updateGameRecords(String player1Name, String player2Name, String Winner) {
		// TODO Auto-generated method stub
		System.out.println("Model | GameLeaderBoard | updateGameRecords P1- "+player1Name+" P2- "+player2Name+" Win- "+Winner );
		
		//Connecting to Db Operations
		RPSM_DBOperations operations = new RPSM_DBOperations();
		try {
			operations.insertGameRecords(player1Name, player2Name, Winner);
			operations.getGameRecords(getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Model | GameLeaderBoard | updateGameRecords | Error");
			e.printStackTrace();
		}
		
		
	}
	/**
	 * Update Player records
	 * @param results res == 0 / res == 1 / res == 2
	 * @param player1Name P1 Name
	 * @param player2Name P2 Name
	 * @param playerWins No of wins
	 * @param playerLose No of lose
	 * @throws SQLException Exception
	 */
	public void updatePlayerRecords(int results, String player1Name, String player2Name, int playerWins, int playerLose) throws SQLException {
		// TODO Auto-generated method stub
		RPSM_DBOperations operations = new RPSM_DBOperations();
		System.out.println("Model | GameLeaderBoard | updatePlayerRecords"+" P1- "+player1Name+" P2- "+player2Name+" W- "+playerWins+" L- "+playerLose+" R- "+results);
		switch (results) {
		case 0:
			if(playerWins == 0 & playerLose == 0)
			{
				operations.insertPlayerRecords(player1Name,0,0,1);
				operations.insertPlayerRecords(player2Name,0,0,1);
			}
			
			break;
		case 1:
			operations.insertPlayerRecords(player1Name,playerWins,0,0);
			operations.insertPlayerRecords(player2Name,0,playerLose,0);
			break;
		case 2:
			operations.insertPlayerRecords(player1Name,0,playerLose,0);
			operations.insertPlayerRecords(player2Name,playerWins,0,0);
			break;
		}
		operations.getPlayerRecords(getConnection());
	}
}
