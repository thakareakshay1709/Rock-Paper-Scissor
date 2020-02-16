package ucd.java.rps.model;

/**
 * This model has getters and setters of Game records
 * @author akshaythakare
 * https://www.youtube.com/watch?v=UKzx4NRaZyM
 *
 */
//
public class RPSM_GameRecords {
	private String playerOne,playerTwo,winner,date;
	
	public RPSM_GameRecords(String P1, String P2, String Winner, String Date)
	{
		this.playerOne = P1;
		this.playerTwo = P2;
		this.winner = Winner;
		this.date = Date;
		
	}

	public String getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(String playerOne) {
		this.playerOne = playerOne;
	}

	public String getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(String playerTwo) {
		this.playerTwo = playerTwo;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
