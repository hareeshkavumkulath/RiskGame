/**
 * 
 */
package com.risk.model;

import java.util.ArrayList;

/**
 * @author Hareesh Kavumkulath
 *
 */
public class AttackStatus {
	
	public StringBuffer statusMessage;
	public boolean hasWon;
	public Player winner;
	public Game game;
	
	public StringBuffer getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(StringBuffer statusMessage) {
		this.statusMessage = statusMessage;
	}
	public boolean isHasWon() {
		return hasWon;
	}
	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}
	public Player getWinner() {
		return winner;
	}
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}	

}
