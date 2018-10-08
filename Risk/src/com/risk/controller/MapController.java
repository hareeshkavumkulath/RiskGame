package com.risk.controller;

import java.util.ArrayList;

import com.risk.model.Continent;
import com.risk.model.Territory;

/**
 * Controller class for the Map Validation, Map Update and Map Save
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 * 
 * @date 10-08-2018
 * @modifiedDate 
 * @modifiedBy
 */
public class MapController {
	
	public static StringBuffer fileContent = new StringBuffer();
	public static ArrayList<Continent> continentArray = new ArrayList<Continent>();
	public static ArrayList<Territory> countriesArray = new ArrayList<Territory>();
	public static boolean isValidMap = false;
	public static StringBuffer message = new StringBuffer();	

}
