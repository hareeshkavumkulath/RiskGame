package com.risk.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model class for Continent
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 * 
 */
public class Continent implements Serializable{
	
	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = 5674800133017682098L;
	@SuppressWarnings("javadoc")
	String name;
	@SuppressWarnings("javadoc")
	int numberOfArmies;
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
	 * 
	 * @return String name as a string
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter of name of the continent
	 * 
	 * @param name name of the continent
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for the number of armies in the continent
	 * 
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
	 * Getter for territories
	 * 
	 * @return ArrayList list of territories
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
