package com.risk.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.risk.model.Continent;
import com.risk.model.MapMessage;
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
	public static ArrayList<Territory> territoriesArray = new ArrayList<Territory>();
	public static boolean isValidMap = false;
	public static StringBuffer message = new StringBuffer();	
	
	/**
	 * Process the .map file and checks whether its valid or not. 
	 * If it is valid return territories and continents
	 * If there is any error, return error message 
	 * 
	 * @param file
	 * @return MapMessage with information about the Map
	 */
	public static MapMessage processFile(File file) {
		FileReader br = null;
		try {

            Scanner input = new Scanner(file);
            
            while (input.hasNext()) {
            	String line = input.nextLine();
                if (!line.isEmpty()) {
                	fileContent.append(line).append("\n");
                }
            }

            input.close();

        } catch (FileNotFoundException ex) {
        	ex.printStackTrace();
        }
		
		// Processing continents - Whether the continents are available in the .map file
		boolean isValidContinents = false;
		isValidContinents = processContinents(fileContent.toString());
		if(isValidContinents) {
			// Processing territories -  Whether the territories are available in the .map file
			boolean isValidTerritories = false;
			isValidTerritories = processTerritories(fileContent.toString());
			if(isValidTerritories) {
				//connecting territories to continents
				boolean valid = false;
				valid = territoriesToContinents();
				if(valid) {
					
				}
			}else { // else of isValidTerritories
				isValidMap = false;
			}
		}else {  // else of isValidContinents
			isValidMap = false;
		}
		return null;
	}
	
	
	/**
	 * Function to process the content of the .map file and see whether there are Continents available 
	 * If yes, it will get added to ArrayList of Continents
	 * 
	 * @param mapInfo
	 * @return
	 */
	public static boolean processContinents(String mapInfo) {
		boolean valid = false;
		if(mapInfo.contains("[Continents]")) {
			try {
				int indexOfContinents = mapInfo.indexOf("[Continents]");
				int indexOfTerritories = mapInfo.indexOf("[Territories]");
				String continents = mapInfo.substring(indexOfContinents, indexOfTerritories);
				String continent[] = continents.split("\n");
				for(int i=1;i<continent.length;i++) {
					continent[i].trim();
					String[] continentData = continent[i].split("=");
					Continent continent1 = new Continent(continentData[0], Integer.parseInt((continentData[1])));
					continentArray.add(continent1);
				}
				valid = true;
			}catch(Exception e) {
				valid = false;
				message.append("There is some error in the syntax of the continents, Please recheck");
			}
		}else {
			valid = false;
			message.append("Map has no continents");
		}
		return valid;
	}
	
	/**
	 * Function to process the content of the .map file and see whether there are Territories are available
	 * If yes it will return ArrayList of Territories 
	 * 
	 * @param mapInfo
	 * @return
	 */
	public static boolean processTerritories(String mapInfo) {
		boolean isValidTerritories = false;
		if(mapInfo.contains("[Territories]")) {
			try {
				int indexOfTerritories = mapInfo.indexOf("[Territories]");
				String territories = mapInfo.substring(indexOfTerritories);
				String territoryInfo[] = territories.split("\n");
				for(int i=1;i<territoryInfo.length;i++) {
					territoryInfo[i].trim();
					String[] territoryDetails = territoryInfo[i].split(",");
		    		Territory newTerritory = new Territory(territoryDetails[0], territoryDetails[3]);
		    		ArrayList<String> adjacentCountries = new ArrayList<String>();
		    		for(int j=4;j<territoryDetails.length;j++) {
		    			adjacentCountries.add(territoryDetails[j]);
		    		}
		    		newTerritory.setAdjacentTerritories(adjacentCountries);
		    		territoriesArray.add(newTerritory);
				}
				isValidTerritories = true;
			}catch(Exception e) {
				message.append("There is some error in the syntax of the territories, Please recheck");
				isValidTerritories = false;
			}			
		}else {
			message.append("Map has no territories");
			isValidTerritories = false;
		}
		return isValidTerritories;
	}
	
	/**
	 * Add each countries to the corresponding Continents
	 * 
	 * @return true if all territories are assigned to Continents, if there is any territory left return false
	 */
	public static boolean territoriesToContinents() {
		boolean valid = false;
		int count = 0;
		try {
			for(int i = 0; i<territoriesArray.size();i++) {
				String continentName = territoriesArray.get(i).getContinent();
				int index = indexOfContinent(continentName);
				if(index >= 0) {
					continentArray.get(index).addTerritories(territoriesArray.get(i));
					count++;
				}else {
					valid = false;
					message.append("The continent, " + continentName + " is not available in the map.");
				}
			}
		}catch(Exception e) {
			valid = false;
			message.append("There is some error in attaching countries to continents");
		}
		if(count != territoriesArray.size()) {
			valid = false;
			message.append("Unable to add some countries to continents");
		}else {
			valid = true;
		}
		return valid;
	}
	
	/**
	 * Function to return the index of the continent in the continent array
	 * 
	 * @param obj
	 * @return index of the object
	 */
	public static int indexOfContinent(Object obj) {
		if (obj == null) {
			for (int i = 0; i < continentArray.size(); i++) {
				if (continentArray.get(i)==null) {
					return i;
				}                    
			}                
		} else {
			for (int i = 0; i < continentArray.size(); i++) {
				if (obj.equals(continentArray.get(i).getName())) {
					return i;
				}
			}
		}
		return -1;
	}

}
