/**
 * 
 */
package com.risk.model;

import java.util.Observable;

/**
 * @author Hareesh Kavumkulath
 *
 */
public class CardsObservable extends Observable {
	
	public Player player;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		setChanged();
		notifyObservers(this);
	}

}
