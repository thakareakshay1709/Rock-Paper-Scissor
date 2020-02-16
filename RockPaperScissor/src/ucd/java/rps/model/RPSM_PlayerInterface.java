package ucd.java.rps.model;

/**
 * This public interface is defined to reuse the methods require frequently in model
 * @author akshaythakare
 *
 */
public interface RPSM_PlayerInterface {
	
	public String getPlayerOneChoice();
	public void setPlayerOneChoice(String p1Choice);
	
	public String getPlayerTwoChoice();
	public void setPlayerTwoChoice(String p2Choice);
	
	public String getPlayerOneName();
	public void setPlayerOneName(String p1name);
	
	public String getPlayerTwoName();
	public void setPlayerTwoName(String p2name);
	
	public String toString();

}
