/**
 * 
 */
package com.risk.model;

/**
 * Model class for Cards
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 *
 */
public class Card {
	
	@SuppressWarnings("javadoc")
	public String armyType;
	@SuppressWarnings("javadoc")
	public int numArmies;
	
	/**
	 * Constructor with parameter for Card class.
	 * 
	 * @param armyType the armyType variable will be initialized by constructor
	 */
	public Card(String armyType) {
		super();
		this.armyType = armyType;
	}
	
	/** 
	 * Getter method for the armyType of the Card class
	 * 
	 * @return String armyType of the card
	 */
	public String getArmyType() {
		return armyType;
	}
	
	/**
	 * Setter for the armyType of the Card class
	 * 
	 * @param armyType set the armyType of the card
	 */
	public void setArmyType(String armyType) {
		this.armyType = armyType;
	}
	
	/** 
	 * Getter method for the number of armies of the Card class
	 * 
	 * @return int numArmies of the card
	 */
	public int getNumArmies() {
		return numArmies;
	}
	
	/**
	 * Setter for the name of the player
	 * 
	 * @param numArmies set the number of armies of the card
	 */
	public void setNumArmies(int numArmies) {
		this.numArmies = numArmies;
	}
	
	
	public void addArmy(String armyType) {
		if(armyType.equalsIgnoreCase("Infantry")) {
			this.numArmies = 1;
		}else if(armyType.equalsIgnoreCase("Cavalry")) {
			this.numArmies = 5;
		}else {
			this.numArmies = 10;
		}
	}
	
}
