package com.risk.model;

import java.util.ArrayList;

/**
 * Model class for Player
 * 
 * @author Hareesh Kavumkulath
 *
 */
public class Player {
	
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
	
	/**
	 * Constructor for Player
	 * 
	 * @param name name of the player
	 * @param isComputer true/false based on whether the player is computer or human
	 * @param numberOfArmies number of armies alloated to player
	 */
	public Player(String name, boolean isComputer, int numberOfArmies) {
		this.name = name;
		this.isComputer = isComputer;
		this.numberOfArmies = numberOfArmies;
		this.fortificationStatus = false;
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
	 * @param name of the player
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
	 * @param numberOfArmies int number of armies
	 */
	public void setNumberOfArmies(int numberOfArmies) {
		this.numberOfArmies = numberOfArmies;
	}
	/**
	 * Getter for the owned territories
	 * 
	 * @return ArrayList<Territory> ArrayList of the owned territories
	 */
	public ArrayList<Territory> getOwnedTerritories() {
		return ownedTerritories;
	}
	/**
	 * Setter for the owned territories
	 * 
	 * @param ownedTerritories ArrayList of owned territories
	 */
	public void setOwnedTerritories(ArrayList<Territory> ownedTerritories) {
		this.ownedTerritories = ownedTerritories;
	}
	/**
	 * Getter for the Owned Continents
	 * 
	 * @return ArrayList<Continent> ArrayList of continents
	 */
	public ArrayList<Continent> getOwnedContinents() {
		return ownedContinents;
	}
	/**
	 * Setter for the Owned Continents
	 * 
	 * @param ownedContinents ArrayList of Continents
	 */
	public void setOwnedContinents(ArrayList<Continent> ownedContinents) {
		this.ownedContinents = ownedContinents;
	}
	/**
	 * Returns whether the player is computer or human
	 * 
	 * @return boolean true/false
	 */
	public boolean isComputer() {
		return isComputer;
	}
	/**
	 * Sets true is the player is computer else false
	 * 
	 * @param isComputer boolean true/false
	 */
	public void setComputer(boolean isComputer) {
		this.isComputer = isComputer;
	}
	/**
	 * Gets the current fortification status
	 * 
	 * @return boolean true/false
	 */
	public boolean isFortificationStatus() {
		return fortificationStatus;
	}
	/**
	 * Sets true if the player has completed fortification else false
	 * 
	 * @param fortificationStatus true/false
	 */
	public void setFortificationStatus(boolean fortificationStatus) {
		this.fortificationStatus = fortificationStatus;
	}	

}
