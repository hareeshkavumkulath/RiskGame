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
	public ArrayList<Player> playerList;
	
	public AttackStatus() {
		super();
		this.statusMessage = new StringBuffer("");
		this.hasWon = false;
		this.winner = null;
	}
	public StringBuffer getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(StringBuffer statusMessage) {
		this.statusMessage = statusMessage;
	}
	public boolean hasWon() {
		return hasWon;
	}
	public void hasWon(boolean status) {
		this.hasWon = status;
	}
	public Player getWinner() {
		return winner;
	}
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	public boolean isHasWon() {
		return hasWon;
	}
	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

}
