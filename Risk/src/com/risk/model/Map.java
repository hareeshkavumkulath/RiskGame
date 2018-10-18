package com.risk.model;

import java.util.ArrayList;

/**
 * Map class which returns Map object with following informations
 * 
 * <ul>
 * <li>Continents ArrayList</li>
 * <li>Territories ArrayList</li>
 * </ul>
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 *
 */
public class Map {
	
	@SuppressWarnings("javadoc")
	ArrayList<Continent> continents = new ArrayList<Continent>();
	@SuppressWarnings("javadoc")
	ArrayList<Territory> territories = new ArrayList<Territory>();
	
	/**
	 * Constructor
	 * 
	 * @param continents ArrayList of continents
	 * @param territories ArrayList of territories
	 */
	public Map(ArrayList<Continent> continents, ArrayList<Territory> territories) {
		super();
		this.continents = continents;
		this.territories = territories;
	}
	
	/**
	 * Getter for continents
	 * 
	 * @return ArrayList<Continent> ArrayList of continents
	 */
	public ArrayList<Continent> getContinents() {
		return continents;
	}
	/**
	 * Setter for continents
	 * 
	 * @param continents ArrayList of continents
	 */
	public void setContinentsList(ArrayList<Continent> continents) {
		this.continents = continents;
	}
	
	/**
	 * Getter for territories 
	 * 
	 * @return ArrayList<Territory> ArrayList of territories
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

}
