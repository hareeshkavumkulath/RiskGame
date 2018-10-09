/**
 * 
 */
package com.risk.model;

import java.util.ArrayList;

/**
 * Model class for the Territory
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 * 
 * @date 10-07-2018
 * @modifiedDate 10-07-2018
 * @modifiedBy jingya Pan
 *
 */
public class Territory {
	
	String name;
	String continent;
	ArrayList<String> adjacentTerritories;
	/**
	 * Create a Territory with territory name and continent name
	 * @param name - Name of the Territory
	 * @param continent - Name of the Continent
	 */
	public Territory(String name, String continent) {
		super();
		this.name = name;
		this.continent = continent;
	}
	
	/**
	 * 
	 */
	public Territory() {
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getContinent() {
		return continent;
	}
	
	public void setContinent(String continent) {
		this.continent = continent;
	}
	
	public ArrayList<String> getAdjacentTerritories() {
		return adjacentTerritories;
	}
	
	public void setAdjacentTerritories(ArrayList<String> adjacentTerritories) {
		this.adjacentTerritories = adjacentTerritories;
	}	

}
