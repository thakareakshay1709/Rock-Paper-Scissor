package ucd.java.rps.model;

import java.sql.SQLException;

/**
 * This is main model implements Player Interface and used to implement main logic of the game like 
 * getting the winner and its record
 * @author akshaythakare
 *
 */
public class RPSM_Main implements RPSM_PlayerInterface{
	
	private int ONE = 1, ZERO = 0, TWO = 2,  result, playerOneWins = 0, playerTwoWins = 0, playerOneLose = 0, playerTwoLose = 0, gamesPlayed = 0; //round,
	

	private String playerOneName, playerTwoName, playerOneChoice, playerTwoChoice,stats;

	public RPSM_Main()
	{
		System.out.println("Model | Main | Dummy Constructor");
	}

	
	public RPSM_Main(String playerOneName, String playerOneChoice, String playerTwoName, String playerTwoChoice) {
		System.out.println("****Entered in Model - Main****");
		//this.player1Name = playerOneName.toUpperCase();
		setPlayerOneName(playerOneName.toUpperCase());
		
		//this.player1Choice =playerOneChoice;
		setPlayerOneChoice(playerOneChoice);
		
		//this.player2Name = playerTwoName.toUpperCase();
		setPlayerTwoName(playerTwoName.toUpperCase());
		
		//this.player2Choice = playerTwoChoice;
		setPlayerTwoChoice(playerTwoChoice);
		
		//setting status
		//result = gameResults(this.player1Choice, this.player2Choice);
		result = gameResults(getPlayerOneChoice(), getPlayerTwoChoice());
		//System.out.println("Result "+result);
		setResult(result);
		
		//setting game stats
		//stats = gameStats(result,this.player1Name,this.player2Name);
		stats = gameStats(getResult(),getPlayerOneName(),getPlayerTwoName());
		
		//Setting Leader Board
		
		
		  try {
		  setLeaderBoard(getResult(),getPlayerOneName(),getPlayerTwoName(),getPlayerOneWins
		  (),getPlayerTwoWins(),getPlayerOneLose(),getPlayerTwoLose(),getGamesPlayed())
		  ; } catch (SQLException e) { // TODO Auto-generated catch block
		  System.out.println(" Error in setting leader Board"); e.printStackTrace(); }
		 
		
	}


	private void setLeaderBoard(int result, String player1Name, String player2Name, int playerOneWins,
			int playerTwoWins, int playerOneLose, int playerTwoLose, int gamesPlayed) throws SQLException {
		// TODO Auto-generated method stub
		String tied = "Tie";
		int zero = 0;
		RPSM_GameLeaderBoard leaderboard = new RPSM_GameLeaderBoard();
		if(result == 1)
		{
			leaderboard.updateGameRecords(player1Name,player2Name,player1Name);
			leaderboard.updatePlayerRecords(result,player1Name,player2Name,playerOneWins,playerTwoLose);
		}
		
		else if (result == 2)
		{
			leaderboard.updateGameRecords(player1Name,player2Name,player2Name);
			leaderboard.updatePlayerRecords(result,player1Name,player2Name,playerTwoWins,playerOneLose);
		}
		else if (result == 0)
		{
			leaderboard.updateGameRecords(player1Name,player2Name,tied);
			leaderboard.updatePlayerRecords(result, player1Name,player2Name,zero,zero);
		}
		
	}


	public String gameStats(int result, String player1Name, String player2Name) {
		System.out.println("Model | Main | gameStats");
		switch (result) {
		case 0:
			stats = "Game tied.!";
			//gamesPlayed++;
			setGamesPlayed(++gamesPlayed);
			System.out.println("Model | Main | gameStats P1Wins-"+getPlayerOneWins()+" P2Wins-"+getPlayerTwoWins()+" GPlayed-"+getGamesPlayed());
			break;
		case 1:
			stats = player1Name+" won the game";
			//gamesPlayed++;
			setGamesPlayed(++gamesPlayed);
			playerOneWins = playerOneWins + 1;
			setPlayerOneWins(playerOneWins);
			setPlayerTwoLose(++playerTwoLose);
			if(playerTwoWins > 0)
			{
				playerTwoWins = playerTwoWins - 1;
				setPlayerTwoWins(playerTwoWins);
			}
			System.out.println("Model | Main | gameStats P1Wins-"+getPlayerOneWins()+" P2Wins-"+getPlayerTwoWins()+" GPlayed-"+getGamesPlayed());
			break;
		case 2:
			stats = player2Name+" won the game";
			//gamesPlayed++;
			setGamesPlayed(++gamesPlayed);
			playerTwoWins = playerTwoWins + 1;
			setPlayerOneLose(++playerOneLose);
			setPlayerTwoWins(playerTwoWins);
			if(playerOneWins > 0)
			{
				playerOneWins = playerOneWins - 1;
				setPlayerOneWins(playerOneWins);
			}
			System.out.println("Model | Main | gameStats P1Wins-"+getPlayerOneWins()+" P2Wins-"+getPlayerTwoWins()+" GPlayed-"+getGamesPlayed());
			break;
		}
		return stats;
	}

	
/**
 * This method is created to get the game results as per below logic
 * scissor beats paper
 * paper beats rock
 * rock beats scissor
 * 
 * @param playerOneChoice PlayerOne CHoice
 * @param playerTwoChoice PlayerTwo Choice
 * @return int 0 if game tie, 1 if playerone wins, 2 if playertwo wins
 */
	public int gameResults(String playerOneChoice, String playerTwoChoice) {
		//getting the next player choice
		//next = nextPlayer.getNextPlayerChoice();
		//logic
		//scissor beats paper
		//paper beats rock
		//rock beats scissor
		System.out.println("Model | Main | gameResults");
		switch (playerOneChoice) {
		case "rock":
			
			if(playerTwoChoice.equals("scissor"))
			{
				//System.out.println("Player 1 won");
				return ONE;
			}
			else if (playerTwoChoice.equals("paper"))
			{
				//System.out.println("Player 2 won");
				return TWO;
			}
			else
			{
				//System.out.println("Game Tie");
				return ZERO;
			}
			
		case "paper":
			if(playerTwoChoice.equals("scissor"))
			{
				//System.out.println("Player 2 won");
				return TWO;
			}
			else if (playerTwoChoice.equals("rock"))
			{
				//System.out.println("Player 1 won");
				return ONE;
			}
			else
			{
				//System.out.println("Game Tie");
				return ZERO;
			}
			
		case "scissor":
			if(playerTwoChoice.equals("paper"))
			{
				//System.out.println("Player 1 won");
				return ONE;
			}
			else if (playerTwoChoice.equals("rock"))
			{
				//System.out.println("Player 2 won");
				return TWO;
			}
			else
			{
				//System.out.println("Game Tie");
				return ZERO;
			}
			//break;

		default:
			throw new IllegalArgumentException("Invalid input: " + playerOneChoice);
		}
		
	}
	
	@Override
	public String toString()
	{
		return stats;
	}
	
	@Override
	public String getPlayerOneName() {
		return this.playerOneName;
	}

	@Override
	public void setPlayerOneName(String player1Name) {
		this.playerOneName = player1Name;
	}

	@Override
	public String getPlayerTwoName() {
		return this.playerTwoName;
	}

	@Override
	public void setPlayerTwoName(String player2Name) {
		this.playerTwoName = player2Name;
	}

	@Override
	public String getPlayerOneChoice() {
		return this.playerOneChoice;
	}

	@Override
	public void setPlayerOneChoice(String player1Choice) {
		this.playerOneChoice = player1Choice;
	}

	@Override
	public String getPlayerTwoChoice() {
		return this.playerTwoChoice;
	}

	@Override
	public void setPlayerTwoChoice(String player2Choice) {
		this.playerTwoChoice = player2Choice;
	}
	
	
	public int getPlayerOneWins() {
		return playerOneWins;
	}

	public void setPlayerOneWins(int playerOneWins) {
		this.playerOneWins = playerOneWins;
	}

	public int getPlayerTwoWins() {
		return playerTwoWins;
	}

	public void setPlayerTwoWins(int playerTwoWins) {
		this.playerTwoWins = playerTwoWins;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
	public int getPlayerOneLose() {
		return playerOneLose;
	}


	public void setPlayerOneLose(int playerOneLose) {
		this.playerOneLose = playerOneLose;
	}


	public int getPlayerTwoLose() {
		return playerTwoLose;
	}


	public void setPlayerTwoLose(int playerTwoLose) {
		this.playerTwoLose = playerTwoLose;
	}



}
