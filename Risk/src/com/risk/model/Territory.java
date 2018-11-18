package com.risk.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model class for the Territory
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0 
 */
public class Territory implements Serializable {
	
	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = 339137798507186038L;
	@SuppressWarnings("javadoc")
	String name;
	@SuppressWarnings("javadoc")
	String continent;
	@SuppressWarnings("javadoc")
	ArrayList<Territory> adjacentTerritories;
	@SuppressWarnings("javadoc")
	Player ruler;
	@SuppressWarnings("javadoc")
	int numberOfArmies;
	/**
	 * Create a Territory with territory name and continent name
	 * 
	 * @param name - Name of the Territory
	 * @param continent - Name of the Continent
	 * @param numberOfArmies  - Number of the armies - initially 0
	 */
	public Territory(String name, String continent, int numberOfArmies) {
		super();
		this.name = name;
		this.continent = continent;
		this.numberOfArmies = numberOfArmies;
	}
	
	/**
	 * Constructor 
	 */
	public Territory() {
	}

	/**
	 * Getter for name
	 * 
	 * @return String name of the Territory
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for name
	 * 
	 * @param name name of the Territory
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter of continent
	 * 
	 * @return String continent of the territory
	 */
	public String getContinent() {
		return continent;
	}
	
	/**
	 * Getter for continent
	 * 
	 * @param continent Continent of the territory
	 */
	public void setContinent(String continent) {
		this.continent = continent;
	}
	
	/**
	 * Getter for adjacent territories
	 * 
	 * @return ArrayList list of adjacent territories
	 */
	public ArrayList<Territory> getAdjacentTerritories() {
		return adjacentTerritories;
	}
	
	/**
	 * Setter for adjacent territories
	 * 
	 * @param adjacentTerritories ArrayList of adjacent territories
	 */
	public void setAdjacentTerritories(ArrayList<Territory> adjacentTerritories) {
		this.adjacentTerritories = adjacentTerritories;
	}

	/**
	 * Getter for Player/Ruler
	 * 
	 * @return Player player/ruler of the territory
	 */
	public Player getRuler() {
		return ruler;
	}

	/**
	 * Set Player/ruler for the territory
	 * 
	 * @param ruler Player/Ruler
	 */
	public void setRuler(Player ruler) {
		this.ruler = ruler;
	}

	/**
	 * Getter for number of armies
	 * 
	 * @return int number of armies
	 */
	public int getNumberOfArmies() {
		return numberOfArmies;
	}

	/**
	 * Sets the number of armies
	 * 
	 * @param numberOfArmies number of armies
	 */
	public void setNumberOfArmies(int numberOfArmies) {
		this.numberOfArmies = numberOfArmies;
	}

}
