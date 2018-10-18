package com.risk.model;

import java.util.ArrayList;

/**
 * Return type of Map Validation method contains ArrayList of Continents,
 * ArrayList of countries, error messages if any and validation status.
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 * 
 * @date 10-08-2018
 * @modifiedDate 
 * @modifiedBy
 *
 */
public class MapMessage {
	
	public ArrayList<Territory> territories = new ArrayList<Territory>();
	public ArrayList<Continent> continents = new ArrayList<Continent>();
	public boolean isValidMap;
	public StringBuffer message;
	
	public MapMessage(ArrayList<Territory> territories, ArrayList<Continent> continents, boolean isValidMap,
			StringBuffer message) {
		super();
		this.territories = territories;
		this.continents = continents;
		this.isValidMap = isValidMap;
		this.message = message;
	}
	
	public ArrayList<Territory> getTerritories() {
		return territories;
	}
	public void setCountries(ArrayList<Territory> territories) {
		this.territories = territories;
	}
	public ArrayList<Continent> getContinents() {
		return continents;
	}
	public void setContinents(ArrayList<Continent> continents) {
		this.continents = continents;
	}
	public boolean isValidMap() {
		return isValidMap;
	}
	public void setValidMap(boolean isValidMap) {
		this.isValidMap = isValidMap;
	}
	public StringBuffer getMessage() {
		return message;
	}
	public void setMessage(StringBuffer message) {
		this.message = message;
	}	

}
