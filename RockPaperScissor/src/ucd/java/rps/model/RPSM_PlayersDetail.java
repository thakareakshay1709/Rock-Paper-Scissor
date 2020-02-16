package ucd.java.rps.model;

/**
 * This model implements RPSM_PlayerInterface and receive data collected from all controllers
 * validate again
 * and send it to RPSM_Main to get the game results and stats
 * @author akshaythakare
 *
 */
public class RPSM_PlayersDetail implements RPSM_PlayerInterface{
	//this is for player one statistics
	private String playerOneName,playerTwoName, gameResults;
	private String playerOneChoice,playerTwoChoice;
	
	//private int  result;
	
	public RPSM_PlayersDetail(String p1name, String p2name, String p1Choice, String p2Choice) {
		// TODO Auto-generated constructor stub
		System.out.println("****Entered in Model - Player Details****");
		System.out.println("Model | Player Details | Validating inputs");
		if(detailsOk(p1name,p2name,p1Choice,p2Choice))
		{
			this.playerOneName = p1name.toUpperCase();
			this.playerTwoName = p2name.toUpperCase();
			this.playerOneChoice = p1Choice.toLowerCase();
			this.playerTwoChoice = p2Choice.toLowerCase();
			System.out.println("Model | Player Details | Validation Ok");
			System.out.println("Model | Player Details | Sending values to main model"+getPlayerOneName()+" "+getPlayerOneChoice()+" "+getPlayerTwoName()+" "+getPlayerTwoChoice());
			//Getting game status
			RPSM_Main main = new RPSM_Main(getPlayerOneName(),getPlayerOneChoice(),getPlayerTwoName(),getPlayerTwoChoice());
			gameResults = main.toString();
			System.out.println(gameResults);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	public RPSM_PlayersDetail()
	{
		System.out.println("Model | Player Details | Dummy Constructor");
	}


	/**
	 * This method is used to validate the user details 
	 * @param p1name PlayerOne name
	 * @param p2name PlayerTwo name
	 * @param p1Choice PlayerOne choice
	 * @param p2Choice PlayerTwo choice
	 * @return true if validation is ok / false if not
	 */
	public boolean detailsOk(String p1name, String p2name, String p1Choice, String p2Choice) {
		boolean ok = false;
		String regex ="[A-Za-z\\s]+";
		if(!(p1name.trim().isEmpty() || p2name.trim().isEmpty() || p1Choice.trim().isEmpty() 
				|| p2Choice.trim().isEmpty() ))
		{
			if(p1name.matches(regex) || p2name.matches(regex))
			{
				if((p1Choice.trim().toLowerCase().equals("rock") || p2Choice.trim().toLowerCase().equals("rock") ||
						p1Choice.trim().toLowerCase().equals("paper") || p2Choice.trim().toLowerCase().equals("paper") ||
						p1Choice.trim().toLowerCase().equals("scissor") || p2Choice.trim().toLowerCase().equals("scissor")))
					{
						ok = true;
					}
					else
					{
						ok = false;
						System.out.println("Enter Valid choices: Rock/Paper/Scissor");
					}
			}
			else
			{
				ok = false;
				System.out.println("Enter Valid choices: Rock/Paper/Scissor");
			}
			
			
		}
		return ok;
	}
	
	@Override
	public String toString()
	{
		return "Results- "+this.getPlayerOneName().toUpperCase()+" selected- "+this.getPlayerOneChoice().toUpperCase()+", "+this.getPlayerTwoName().toUpperCase()+" selected-"
	+this.getPlayerTwoChoice().toUpperCase()+", "+gameResults.toUpperCase();
	}

	//Getters and setters of player names
	@Override
	public String getPlayerOneChoice() {
		return playerOneChoice;
	}

	@Override
	public void setPlayerOneChoice(String p1Choice) {
		this.playerOneChoice = p1Choice;
	}
	
	@Override
	public String getPlayerTwoChoice()
	{
		return this.playerTwoChoice;
	}
	
	@Override
	public void setPlayerTwoChoice(String p2Choice) {
		this.playerTwoChoice = p2Choice;
	}
	//
	@Override
	public String getPlayerOneName() {
		return this.playerOneName;
	}

	@Override
	public void setPlayerOneName(String p1name) {
		this.playerOneName = p1name;
	}
	
	@Override
	public String getPlayerTwoName()
	{
		return this.playerTwoName;
	}
	@Override
	public void setPlayerTwoName(String p2name) {
		this.playerTwoName = p2name;
	}
	
}
