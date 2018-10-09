package com.risk.controller;

import java.io.File;
import java.io.FileNotFoundException;
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
					// Check the adjacency of continents and added connection between continents
					boolean isContinentConnected = false;
					isContinentConnected = createContinentConnection();
					if(isContinentConnected) {
						// Check all the territories in the Map are connected or not
						boolean isMapConnected = false;
						isMapConnected = validateContinent();
						if(isMapConnected) {
							System.out.println("isMapConnected");
							isValidMap = true;
						}
					}else {
						isValidMap = false;
					}					
				}
			}else { // else of isValidTerritories
				isValidMap = false;
			}
		}else {  // else of isValidContinents
			isValidMap = false;
		}
		MapMessage mapMessage = new MapMessage(territoriesArray, continentArray, isValidMap, message);
		
		return mapMessage;
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
		    		ArrayList<String> adjacentTerritories = new ArrayList<String>();
		    		for(int j=4;j<territoryDetails.length;j++) {
		    			adjacentTerritories.add(territoryDetails[j]);
		    		}
		    		newTerritory.setAdjacentTerritories(adjacentTerritories);
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
	 * Add each Territories to the corresponding Continents
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
			message.append("There is some error in attaching territories to continents");
		}
		if(count != territoriesArray.size()) {
			valid = false;
			message.append("Unable to add some territories to continents");
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
	
	/**
	 * Check the connection between continents by checking the adjacent territories of each territory
	 * If there is any adjacency from a territory A in continent X to a territory in Y.
	 * Then there is adjacency between continent X and continent Y.
	 * 
	 * @return true if there is no exception.
	 */
	public static boolean createContinentConnection() {
		boolean isConnected = false;
		Continent continent = new Continent();
		int temp = 0;
		try {
			for(int i = 0;i < continentArray.size();i++) {
				continent = continentArray.get(i);
				for(int j = 0;j < continent.getTerritories().size();j++) {
					Territory territory = continent.getTerritories().get(j);
					for(int k = 0; k < territory.getAdjacentTerritories().size(); k++) {
						String adjacentTerritory = territory.getAdjacentTerritories().get(k);
						if(!isAdjacentTerritoryInSameContinent(continent.getName(), adjacentTerritory)) {
							Continent adjacentTerritoryContinent = getContinentFromTerritory(adjacentTerritory);
							if(temp == 0) {
								continentArray.get(i).getAdjacentContinents().add(adjacentTerritoryContinent);
								temp++;
							}else {
								boolean isExist = false;
								try {
									for(int l=0;l<temp;l++) {
										if(adjacentTerritoryContinent.getName().equals(continentArray.get(i).getAdjacentContinents().get(l).getName())) {
											isExist = true;
										}
									}
								}catch(Exception e) {
									isExist = true;
								}
								if(!isExist) {
									continentArray.get(i).getAdjacentContinents().add(adjacentTerritoryContinent);
									temp++;
								}
							}
						}
					}
				}
				temp = 0;				
			}
			isConnected = true;
		}catch(Exception e) {
			isConnected = false;
			message.append("There is some error connecting territories/continents. Please try again");
		}
		return isConnected;
	}
	
	/**
	 * Check whether the adjacent territory of a territory located in the same continent
	 * 
	 * @param continentName
	 * @param adjacentTerritory
	 * @return 
	 */
	private static boolean isAdjacentTerritoryInSameContinent(String continentName, String adjacentTerritory) {
		Continent continent = new Continent();
		boolean isAdjacentTerritoryInSameContinent = false;
		for(int i = 0;i < continentArray.size();i++) {
			if(continentArray.get(i).getName().equals(continentName)){
				continent = continentArray.get(i);
			}
		}
		for(int i = 0;i < continent.getTerritories().size();i++) {
			if(adjacentTerritory.equals(continent.getTerritories().get(i).getName())){
				isAdjacentTerritoryInSameContinent = true;
			}
		}
		return isAdjacentTerritoryInSameContinent;
	}
	
	/**
	 * Return the continent of the territory
	 * 
	 * @param adjacentTerritory
	 * @return - Continent
	 */
	private static Continent getContinentFromTerritory(String adjacentTerritory) {
		Continent continent = new Continent();
		for(int i = 0; i < territoriesArray.size();i++) {
			if(adjacentTerritory.equals(territoriesArray.get(i).getName())) {
				String continentName = territoriesArray.get(i).getContinent();
				continent = getContinentFromArray(continentName);
			}
		}
		return continent;
	}
	
	/**
	 * Returns the Continent from the continentsArray, ArrayList
	 * 
	 * @param continentName
	 * @return
	 */
	private static Continent getContinentFromArray(String continentName) {
		Continent continent = new Continent();
		for(int i = 0; i < continentArray.size();i++) {
			if(continentName.equals(continentArray.get(i).getName())) {
				continent = continentArray.get(i);
			}
		}
		return continent;
	}
	
	/**
	 * Check whether all the territories are well connected in the Map
	 * Used DFS algorithm to check the connection and traversal of each territories
	 *  
	 * @return
	 */
	private static boolean validateContinent() {
		boolean isConnected = false;
		int numberOfTerritories = territoriesArray.size();
		ArrayList<String> visitedTerritories = new ArrayList<String>();
		ArrayList<String> checkedTerritories = new ArrayList<String>();
		Territory territory = (Territory)territoriesArray.get(1);
		visitedTerritories.add(territory.getName());
		checkedTerritories.add(territory.getName());
		int index = 1;
		System.out.print(territory.getName()+"===");
		while(!checkedTerritories.isEmpty()) {
			Territory newTerritory = getTerritory(checkedTerritories.get(0));
			System.out.println(index + "==" + newTerritory.getName());
			for(int k = 0;k < newTerritory.getAdjacentTerritories().size();k++) {
				String adjacentTerritory = newTerritory.getAdjacentTerritories().get(k);
				if(visitedTerritories.indexOf(adjacentTerritory) < 0 ) {
					visitedTerritories.add(adjacentTerritory);
					System.out.print(adjacentTerritory+",");
				}
			}
			if(visitedTerritories.size() != numberOfTerritories) {
				checkedTerritories.remove(0);
				try {
					checkedTerritories.add(visitedTerritories.get(index));
				}catch(Exception e) {
					
				}
				index++;
			}else {
				checkedTerritories.remove(0);
				isConnected = true;
			}
		}
		if(!isConnected) {
			message.append("Territories are not well connected");
			isConnected = false;
		}
		return isConnected;		
	}
	
	/**
	 * Return territory using the territoryName
	 * 	
	 * @param territoryName
	 * @return
	 */
	public static Territory getTerritory(String territoryName) {
		Territory territory = null;
		for(int i = 0;i<territoriesArray.size();i++) {
			if(territoryName.equals(territoriesArray.get(i).getName())) {
				territory = (Territory)territoriesArray.get(i);
			}
		}
		return territory;
	}
	
	/**
	 * Remove continent with name, continentName
	 * 
	 * @param continentName
	 * @return
	 */
	public static MapMessage removeContinent(String continentName) {
		boolean isRemoved = removeContinent(getContinentFromArray(continentName)); 
		MapMessage mapMessage = new MapMessage(territoriesArray, continentArray, isRemoved, message);
		return mapMessage;
	}
	
	/**
	 * Remove continent if the user wants to remove it. Removes continent from the arraylist
	 * <p>It consists of following processes</p>
	 * <ul>
	 * <li>Remove adjacency between continents</li>
	 * <li>Remove all countries from the continent one by one</li>
	 * </ul>
	 * 
	 * @param continent
	 * @return
	 */
	public static boolean removeContinent(Continent continent) {
		boolean isRemoved = true;
		try {
			//Remove adjacency between continents
			removeContinentAdjacency(continent);
			//Remove all territories from the continent
			removeTerritoriesFromContinent(continent);
			continentArray.remove(continent);
		}catch(Exception e) {
			isRemoved = false;
		}
		return isRemoved;
	}
	
	/**
	 * Remove adjacency of continent
	 * <p>If the user wants to delete the continent X from the map. If it has adjacency with continents A, B and C.
	 * Then, go to continent A, B and C and remove X from their adjacent continents
	 * </p> 
	 * 
	 * @param continent
	 */
	public static void removeContinentAdjacency(Continent continent) {
		for(int i=0;i<continentArray.size();i++) {
			if(continentArray.get(i).getAdjacentContinents().contains(continent)) {
				continentArray.get(i).getAdjacentContinents().remove(continent);
			}
		}
	}
	
	/**
	 * Remove all territories of the continent
	 * 
	 * @param continent
	 */
	public static void removeTerritoriesFromContinent(Continent continent) {
		ArrayList<Territory> territoriesInContinent = continent.getTerritories();
		for(int i=0;i<territoriesInContinent.size();i++) {
			removeTerritory(territoriesInContinent.get(i));
		}
	}
	
	/**
	 * Remove a single territory
	 * <p>It consists of following processes</p>
	 * <ul>
	 * <li>Remove adjacency between territories</li>
	 * <li>Remove the territory from the continent</li>
	 * </ul>
	 * 
	 * @param territory
	 * @return
	 */
	public static boolean removeTerritory(Territory territory) {
		boolean isRemoved = true;
		try {
			String territoryName = territory.getName();
			for(int i = 0;i<territoriesArray.size();i++) {
				Territory newTerritory = (Territory)territoriesArray.get(i);
				if(territory.equals(newTerritory)) {
					ArrayList<String> adjacentTerritories = newTerritory.getAdjacentTerritories();
					for(int j = 0;j<adjacentTerritories.size();j++) {
						String adjacenTerritoryName = adjacentTerritories.get(j);
						// Remove the adjacency between two territories
						removeTerritoryAdjacency(territoryName, adjacenTerritoryName);
					}
				}
			}
			Continent continent = findContinentOfTerritory(territory);
			removeTerritoryFromContinent(continent, territory);
			territoriesArray.remove(territory);
		}catch(Exception e) {
			isRemoved = false;
		}
		return isRemoved;
	}
	
	/**
	 * Remove the adjacency between two territories.
	 * <p> If the user wants to delete territory A, and if A has adjacency with B and C, then go to the territories
	 * B and C and delete A from their adjacent territories list.
	 * </p>
	 * 
	 * @param territoryName
	 * @param adjacentTerritoryName
	 */
	public static void removeTerritoryAdjacency(String territoryName, String adjacentTerritoryName) {
		for(int i = 0;i<territoriesArray.size();i++) {
			if(adjacentTerritoryName.equals(territoriesArray.get(i).getName())) {
				territoriesArray.get(i).getAdjacentTerritories().remove(territoryName);
			}
		}
	}
	
	/**
	 * Find the continent of a territory
	 * 
	 * @param territory
	 * @return continent
	 */
	public static Continent findContinentOfTerritory(Territory territory) {
		Continent continent = null;
		for(int i=0;i<continentArray.size();i++) {
			if(continentArray.get(i).getTerritories().contains(territory)) {
				continent = continentArray.get(i);
			}
		}
		return continent;
	}
	
	/**
	 * Remove territory from the continent
	 * 
	 * @param continent
	 * @param territory
	 */
	public static void removeTerritoryFromContinent(Continent continent, Territory territory) {
		continent.getTerritories().remove(territory);	
	}

}
