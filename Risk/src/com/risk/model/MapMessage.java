package com.risk.model;

import java.util.ArrayList;

/**
 * Return type of Map Validation method contains ArrayList of Continents,
 * ArrayList of countries, error messages if any and validation status.
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 * 
 */
public class MapMessage {
	
	@SuppressWarnings("javadoc")
	public ArrayList<Territory> territories = new ArrayList<Territory>();
	@SuppressWarnings("javadoc")
	public ArrayList<Continent> continents = new ArrayList<Continent>();
	@SuppressWarnings("javadoc")
	public boolean isValidMap;
	@SuppressWarnings("javadoc")
	public StringBuffer message;
	
	/**
	 * Constructor
	 * 
	 * @param territories ArrayList of territories
	 * @param continents ArrayList of continents
	 * @param isValidMap true if map is valid, else false
	 * @param message String message for invalid maps
	 */
	public MapMessage(ArrayList<Territory> territories, ArrayList<Continent> continents, boolean isValidMap,
			StringBuffer message) {
		super();
		this.territories = territories;
		this.continents = continents;
		this.isValidMap = isValidMap;
		this.message = message;
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
	public void setCountries(ArrayList<Territory> territories) {
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
	public void setContinents(ArrayList<Continent> continents) {
		this.continents = continents;
	}
	/**
	 * Getter for isValidMap
	 * 
	 * @return boolean true if the map is valid, else false
	 */
	public boolean isValidMap() {
		return isValidMap;
	}
	/**
	 * Setter for isValidMap
	 * 
	 * @param isValidMap boolean true if the map is valid, else false
	 */
	public void setValidMap(boolean isValidMap) {
		this.isValidMap = isValidMap;
	}
	/**
	 * Getter for the message for invalid maps
	 * @return StringBuffer message
	 */
	public StringBuffer getMessage() {
		return message;
	}
	/**
	 * Setter for the message for invalid maps
	 * @param message String message
	 */
	public void setMessage(StringBuffer message) {
		this.message = message;
	}	

}
