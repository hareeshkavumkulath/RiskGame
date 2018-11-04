package com.risk.model;

/**
 * @author Hareesh Kavumkulath
 * @version 1.0
 */
public class AttackStatus {
	@SuppressWarnings("javadoc")
	public StringBuffer statusMessage;
	@SuppressWarnings("javadoc")
	public boolean hasWon;
	@SuppressWarnings("javadoc")
	public Player winner;
	@SuppressWarnings("javadoc")
	public Game game;
	/**
	 * Getter method for the getStatusMessage of the AttackStatus class
	 * 
	 * @return StringBuffer statusMessage
	 */
	public StringBuffer getStatusMessage() {
		return statusMessage;
	}
	/**
	 * Setter for the status message of the AttackStatus class
	 * 
	 * @param statusMessage the status message
	 */
	public void setStatusMessage(StringBuffer statusMessage) {
		this.statusMessage = statusMessage;
	}
	/**
	 * return the attack status won or not
	 * 
	 * @return boolean won or not
	 */
	public boolean isHasWon() {
		return hasWon;
	}
	/**
	 * Setter for the attack status
	 * 
	 * @param hasWon boolean type about won or not
	 */
	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}
	/**
	 * Getter for the winner of the class AttackStatus
	 * 
	 * @return Player the winner
	 */
	public Player getWinner() {
		return winner;
	}
	/**
	 * Setter for the winner of the class AttackStatus
	 * 
	 * @param winner the player
	 */
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	/**
	 * Getter for the game of the class AttackStatus
	 * 
	 * @return Game the object
	 */
	public Game getGame() {
		return game;
	}
	/**
	 * Setter for the game of the class AttackStatus
	 * 
	 * @param game the object
	 */
	public void setGame(Game game) {
		this.game = game;
	}	

}
