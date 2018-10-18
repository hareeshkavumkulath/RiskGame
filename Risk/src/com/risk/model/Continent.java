package com.risk.model;

import java.util.ArrayList;

/**
 * Model class for Continent
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 * 
 */
public class Continent {
	
	@SuppressWarnings("javadoc")
	String name;
	@SuppressWarnings("javadoc")
	int numberOfArmies;
	@SuppressWarnings("javadoc")
	ArrayList<Continent> adjacentContinents = new ArrayList<Continent>();
	@SuppressWarnings("javadoc")
	ArrayList<Territory> territories = new ArrayList<Territory>();
	
	/**
	 * Construct a continent with the name and number of Armies
	 * 
	 * @param name name of the continent
	 * @param numberOfArmies number of the reinforcement armies
	 */
	public Continent(String name, int numberOfArmies) {
		this.name = name;
		this.numberOfArmies = numberOfArmies;
	}

	/**
	 * Constructor without any parameters
	 */
	public Continent() {}

	/**
	 * Getter for name of the continent
	 * @return name as a string
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter of name of the continent
	 * @param name name of the continent
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the number of armies in the continent
	 * @return int number of armies
	 */
	public int getNumberOfArmies() {
		return numberOfArmies;
	}

	/**
	 * Setter for the number of armies in the continent
	 * 
	 * @param numberOfArmies int number of armies
	 */
	public void setNumberOfArmies(int numberOfArmies) {
		this.numberOfArmies = numberOfArmies;
	}

	/**
	 * Getter for adjacent continents
	 * 
	 * @return return ArrayList of continents
	 */
	public ArrayList<Continent> getAdjacentContinents() {
		return adjacentContinents;
	}

	/**
	 * Setter for adjacent Continents
	 * 
	 * @param adjacentContinents ArrayList of continents
	 */
	public void setAdjacentContinents(ArrayList<Continent> adjacentContinents) {
		this.adjacentContinents = adjacentContinents;
	}

	/**
	 * Getter for territories
	 * 
	 * @return ArrayList of territories
	 */
	public ArrayList<Territory> getTerritories() {
		return territories;
	}

	/**
	 * Setter for territories
	 * 
	 * @param territories ArrayList of territories
	 */
	public void setTerritories(ArrayList<Territory> territories) {
		this.territories = territories;
	}	
	
	/**
	 * Add territories to the continent
	 * 
	 * @param territory Territory
	 */
	public void addTerritories(Territory territory) {
		territories.add(territory);
	}

}
