/**
 * 
 */
package com.risk.model;

import java.util.Observable;

/**
 * Observable class for Cards Exchange view
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 *
 */
public class CardsObservable extends Observable {
	@SuppressWarnings("javadoc")
	public Player player;
	/**
	 * Getter to get the player object
	 * 
	 * @return Player object
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Setter to set the player
	 * 
	 * @param player Player
	 */
	public void setPlayer(Player player) {
		this.player = player;
		setChanged();
		notifyObservers(this);
	}

}
