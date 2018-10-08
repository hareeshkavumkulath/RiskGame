package com.risk.model;

import java.util.ArrayList;

/**
 * Model class for Continent
 * @author Hareesh Kavumkulath
 * @version 1.0
 * 
 * @date 10-07-2018
 * @modifiedDate 10-07-2018
 * @modifiedBy jingya Pan
 *
 */
public class Continent {
	
	String name;
	int numberOfArmies;
	ArrayList<Continent> adjacentContinents = new ArrayList<Continent>();
	ArrayList<Territory> territories = new ArrayList<Territory>();
	
	/**
	 * Construct a continent with the name and number of Armies
	 * 
	 * @param name
	 * @param numberOfArmies
	 */
	public Continent(String name, int numberOfArmies) {
		this.name = name;
		this.numberOfArmies = numberOfArmies;
	}

	/**
	 * 
	 */
	public Continent() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfArmies() {
		return numberOfArmies;
	}

	public void setNumberOfArmies(int numberOfArmies) {
		this.numberOfArmies = numberOfArmies;
	}

	public ArrayList<Continent> getAdjacentContinents() {
		return adjacentContinents;
	}

	public void setAdjacentContinents(ArrayList<Continent> adjacentContinents) {
		this.adjacentContinents = adjacentContinents;
	}

	public ArrayList<Territory> getTerritories() {
		return territories;
	}

	public void setTerritories(ArrayList<Territory> territories) {
		this.territories = territories;
	}	
	
	public void addTerritories(Territory territory) {
		territories.add(territory);
	}

}
