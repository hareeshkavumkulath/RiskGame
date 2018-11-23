package com.risk.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.risk.controller.GameController;

/**
 * Model class for Player
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 */
public class Player implements Serializable{
	
	/**
	 * Logger object setup for the log file
	 */
	static Logger logger = Logger.getLogger(Player.class.getName());
	
	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = -4223242317148118005L;
	@SuppressWarnings("javadoc")
	public String name;
	@SuppressWarnings("javadoc")
	public int numberOfArmies;
	@SuppressWarnings("javadoc")
	public ArrayList<Territory> ownedTerritories = new ArrayList<Territory>();
	@SuppressWarnings("javadoc")
	public ArrayList<Continent> ownedContinents = new ArrayList<Continent>();
	@SuppressWarnings("javadoc")
	public boolean isComputer;
	@SuppressWarnings("javadoc")
	public boolean fortificationStatus;
	@SuppressWarnings("javadoc")
	public String phase;
	@SuppressWarnings("javadoc")
	public ArrayList<Card> cards = new ArrayList<Card>();
	@SuppressWarnings("javadoc")
	public boolean hasWon = false;
	@SuppressWarnings("javadoc")
	public int turnInCards;
	@SuppressWarnings("javadoc")
	public Strategy strategy;
	
	/**
	 * Constructor for Player
	 * 
	 * @param name player name
	 * @param isComputer player type
	 * @param numberOfArmies number of armies the player have
	 * @param phase String phase of the player
	 */
	public Player(String name, boolean isComputer, int numberOfArmies, String phase) {
		this.name = name;
		this.isComputer = isComputer;
		this.numberOfArmies = numberOfArmies;
		this.fortificationStatus = false;
		this.phase = phase;
		this.turnInCards = 1;
	}
	
	/**
	 *  Empty default constructor 
	 */
	public Player() {
		// TODO Auto-generated constructor stub
	}

	/** 
	 * Getter for the name of the Player
	 * 
	 * @return String name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for the name of the player
	 * 
	 * @param name String pass the name of the player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for the number of armies
	 * 
	 * @return int the number of armies
	 */
	public int getNumberOfArmies() {
		return numberOfArmies;
	}
	
	/**
	 * Setter for the number of armies
	 * 
	 * @param numberOfArmies int pass the number of armies to setter
	 */
	public void setNumberOfArmies(int numberOfArmies) {
		this.numberOfArmies = numberOfArmies;
	}
	
	/**
	 * Get the owned territories in the array list
	 * 
	 * @return ArrayList the list of the owned territories
	 */
	public ArrayList<Territory> getOwnedTerritories() {
		return ownedTerritories;
	}
	
	/**
	 * Set for the owned territories through array list
	 * 
	 * @param ownedTerritories ArrayList of owned territories
	 */
	public void setOwnedTerritories(ArrayList<Territory> ownedTerritories) {
		this.ownedTerritories = ownedTerritories;
	}
	
	/**
	 * Get the Owned Continents through array list continent
	 * 
	 * @return ArrayList list of continents
	 */
	public ArrayList<Continent> getOwnedContinents() {
		return ownedContinents;
	}
	
	/**
	 * Set for the Owned Continents through array list
	 * 
	 * @param ownedContinents ArrayList of Continents
	 */
	public void setOwnedContinents(ArrayList<Continent> ownedContinents) {
		this.ownedContinents = ownedContinents;
	}
	
	/**
	 * Returns whether the player is computer or human
	 * 
	 * @return boolean true or false if the player is computer
	 */
	public boolean isComputer() {
		return isComputer;
	}
	
	/**
	 * Sets true is the player is computer else false
	 * 
	 * @param isComputer boolean true or false 
	 */
	public void setComputer(boolean isComputer) {
		this.isComputer = isComputer;
	}
	
	/**
	 * Gets the current fortification status
	 * 
	 * @return boolean true or false of the fortification status
	 */
	public boolean isFortificationStatus() {
		return fortificationStatus;
	}
	
	/**
	 * Sets true if the player has completed fortification, else false
	 * 
	 * @param fortificationStatus boolean true or false
	 */
	public void setFortificationStatus(boolean fortificationStatus) {
		this.fortificationStatus = fortificationStatus;
	}

	/**
	 * Getter Strategy method
	 * 
	 * @return String of the strategy
	 */
	public Strategy getStrategy() {
		return strategy;
	}

	/**
	 * Setter to set strategy
	 * 
	 * @param strategy set and define the strategy
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	/**
	 * Getter to get cards
	 * 
	 * @return ArrayList list of cards
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}
	/**
	 * Setter to set the cards
	 * 
	 * @param cards the list of cards
	 */
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	/**
	 * whether the player won the game
	 * 
	 * @return boolean true if it wins,false if not
	 */
	public boolean isHasWon() {
		return hasWon;
	}
	/**
	 * set the player won the game
	 * 
	 * @param hasWon won the game or not
	 */
	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}
	/**
	 *Getter the turn in cards
	 *
	 * @return int turnInCards
	 */
	public int getTurnInCards() {
		return turnInCards;
	}
	/**
	 *Setter to set the turn in cards
	 * 
	 * @param turnInCards the turnInCards to set
	 */
	public void setTurnInCards(int turnInCards) {
		this.turnInCards = turnInCards;
	}
	/**
	 * Getter for player's current phase
	 * 
	 * @return the phase
	 */
	public String getPhase() {
		return phase;
	}
	/**
	 * Setter for player's current phase
	 * 
	 * @param phase the phase to set
	 */
	public void setPhase(String phase) {
		this.phase = phase;
	}
	
	/**
	 * Strategy function for reinforce
	 * 
	 * @param territory reinforce Territory
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController
	 */
	public void reinforce(Territory territory, GameInstructions gameInstructions, GameController controller) {
		logger.log(Level.INFO, "Inside reinforce function");
		this.strategy.reinforce(this, territory, gameInstructions, controller);
	}

	/**
	 * Strategy function for Attack
	 * @param attackerTerritory Territory Attacker
	 * @param opponentTerritory Territory Opponent
	 * @param allOutMode boolean 
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController object
	 * @param game Game object
	 */
	public void attack(Territory attackerTerritory, Territory opponentTerritory, boolean allOutMode, GameInstructions gameInstructions, GameController controller) {
		this.strategy.attack(this, attackerTerritory, opponentTerritory, allOutMode, gameInstructions, controller);
		this.setPhase("FORTIFY");
	}
	
	/**
	 * Strategy function for Fortify
	 * 
	 * @param gameInstructions GameInstructions message
	 * @param controller GameController object
	 * @param fortifyNum 
	 * @param toTerritory 
	 * @param fromTerritory 
	 */
	public void fortify(GameInstructions gameInstructions, GameController controller, Territory fromTerritory, Territory toTerritory, int fortifyNum) {
		this.strategy.fortify(this, fromTerritory, toTerritory, fortifyNum, gameInstructions, controller);
		this.setPhase("REINFORCEMENT");
	}
}
