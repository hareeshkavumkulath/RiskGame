package com.risk.model;

import java.util.ArrayList;

/**
 * Model class for Player
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
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
	 * @param isComputer true or false based on whether the player is computer or human
	 * @param numberOfArmies number of armies allocated to player
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
	 * @param name pass the name of the player
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
	 * @param numberOfArmies pass the number of armies to setter
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
	 * @param fortificationStatus true or false
	 */
	public void setFortificationStatus(boolean fortificationStatus) {
		this.fortificationStatus = fortificationStatus;
	}	

}
