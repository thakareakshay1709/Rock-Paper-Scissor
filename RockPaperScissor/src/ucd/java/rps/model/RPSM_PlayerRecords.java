package ucd.java.rps.model;

//Reference//https://www.youtube.com/watch?v=UKzx4NRaZyM

/**
 * This model is used to create a model for getter setter for player records
 * @author akshaythakare
 *
 */

public class RPSM_PlayerRecords {

	private String playerName,totalWins,totalLose,totalTie;
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}



	public String getTotalWins() {
		return totalWins;
	}

	public void setTotalWins(String totalWins) {
		this.totalWins = totalWins;
	}

	public String getTotalLose() {
		return totalLose;
	}

	public void setTotalLose(String totalLose) {
		this.totalLose = totalLose;
	}

	public String getTotalTie() {
		return totalTie;
	}

	public void setTotalTie(String totalTie) {
		this.totalTie = totalTie;
	}

	public RPSM_PlayerRecords(String playerName, String wins,String lose, String tie)
	{
		this.playerName = playerName;
		this.totalWins = wins;
		this.totalLose = lose;
		this.totalTie = tie;
	}
}
