/**
 * 
 */
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
	
	public ArrayList<Territory> countries = new ArrayList<Territory>();
	public ArrayList<Continent> continents = new ArrayList<Continent>();
	public boolean isValidMap;
	public StringBuffer message;

}
