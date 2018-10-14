/**
 * 
 */
package com.risk.model;

import java.util.ArrayList;

/**
 * @author Hareesh Kavumkulath
 *
 */
public class Map {
	
	ArrayList<Continent> continents = new ArrayList<Continent>();
	ArrayList<Territory> territories = new ArrayList<Territory>();
	
	public Map(ArrayList<Continent> continents, ArrayList<Territory> territories) {
		super();
		this.continents = continents;
		this.territories = territories;
	}
	
	public ArrayList<Continent> getContinents() {
		return continents;
	}
	public void setContinentsList(ArrayList<Continent> continents) {
		this.continents = continents;
	}
	public ArrayList<Territory> getTerritories() {
		return territories;
	}
	public void setTerritories(ArrayList<Territory> territories) {
		this.territories = territories;
	}

}
