package com.risk.model;

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
	public Map map = new Map();
	@SuppressWarnings("javadoc")
	public boolean isValidMap;
	@SuppressWarnings("javadoc")
	public StringBuffer message;

	/**
	 * Constructor
	 * 
	 * @param map the map
	 * @param isValidMap true if map is valid, else false
	 * @param message String message for invalid maps
	 */
	public MapMessage(Map map, boolean isValidMap, StringBuffer message) {
		super();
		this.map = map;
		this.isValidMap = isValidMap;
		this.message = message;
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
	 * 
	 * @return StringBuffer message
	 */
	public StringBuffer getMessage() {
		return message;
	}

	/**
	 * Setter for the message for invalid maps
	 * 
	 * @param message String message
	 */
	public void setMessage(StringBuffer message) {
		this.message = message;
	}

	/**
	 * Getter for map
	 * 
	 * @return map the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Setter for the Map
	 * 
	 * @param map the map to set
	 */
	public void setMap(Map map) {
		this.map = map;
	}

}
