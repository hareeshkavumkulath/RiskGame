/**
 * 
 */
package com.risk.model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Master Model class for the Game
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 *
 */
public class Game extends Observable {

	@SuppressWarnings("javadoc")
	public Map map;
	@SuppressWarnings("javadoc")
	public ArrayList<Player> players;
	@SuppressWarnings("javadoc")
	public ArrayList<Card> cards = new ArrayList<Card>();
	@SuppressWarnings("javadoc")
	public Player currentPlayer;

	/**
	 * Constructor for Game
	 * 
	 * @param map the map for the game
	 */
	public Game(Map map) {
		this.map = map;
	}

	/**
	 * Getter for map
	 * 
	 * @return Map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Setter for Map
	 * 
	 * @param map pass map object
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * Getter for Players
	 * 
	 * @return arrayList list of player
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * Setter for Players
	 * 
	 * @param players list of players
	 */
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Update
	 */
	public void update() {
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Returns all cards
	 * 
	 * @return ArrayList of Cards
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}

	/**
	 * Setter for cards
	 * 
	 * @param cards ArrayList of Cards
	 */
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	/**
	 * Getter to get the current player
	 * 
	 * @return Player object
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	/**
	 * Setter to set the current player
	 * 
	 * @param currentPlayer object
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

}
