package com.risk.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.risk.model.Continent;
import com.risk.model.Map;
import com.risk.model.MapMessage;
import com.risk.model.Territory;

/**
 * Controller class for the Map Validation, Map Update and Map Save
 * 
 * @author Hareesh Kavumkulath
 * @version 1.1
 * 
 */
public class MapController {
	
	@SuppressWarnings("javadoc")
	public StringBuffer fileContent = new StringBuffer();
	@SuppressWarnings("javadoc")
	public ArrayList<Continent> continentArray = new ArrayList<Continent>();
	@SuppressWarnings("javadoc")
	public ArrayList<Territory> territoriesArray = new ArrayList<Territory>();
	@SuppressWarnings("javadoc")
	public boolean isValidMap = false;
	@SuppressWarnings("javadoc")
	public StringBuffer message = new StringBuffer();	
	@SuppressWarnings("javadoc")
	public Map map = new Map();
	//====== New Variables ======
	@SuppressWarnings("javadoc")
	public Map map1;
	@SuppressWarnings("javadoc")
	String mapString;
	/**
	 * default constructor of the class MapController
	 */
	public MapController() {
	}
	
	/**
	 * MapController constructor
	 * 
	 * @param fileContent the file input
	 * @param continentArray the arrayList of continents
	 * @param territoriesArray the arrayList of territories
	 * @param isValidMap shows whether the map is valid or not
	 * @param message map message
	 * @param map the map
	 */
	public MapController(StringBuffer fileContent, ArrayList<Continent> continentArray,
			ArrayList<Territory> territoriesArray, boolean isValidMap, StringBuffer message, Map map) {
		super();
		this.fileContent = fileContent;
		this.continentArray = continentArray;
		this.territoriesArray = territoriesArray;
		this.isValidMap = isValidMap;
		this.message = message;
		this.map = map;
	}

	/**
	 * Process the .map file and checks whether its valid or not 
	 * If it is valid return territories and continents
	 * If there is any error, return error message 
	 * 
	 * @param file the input file
	 * @return MapMessage information about the Map
	 */
	public MapMessage processFile(File file) {
		try {

			//========NEW MAP==========
			map1 = setMap(file.getPath());
            //==========OLD CODE=======
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
		//========NEW Validation==========
		MapMessage mapMessage = validateMap();
		return mapMessage;
	}
	
	
	
	
	/**
	 * Check the map is valid or not
	 * 
	 * @param mapInfo the text in the mapTextPane
	 * @return MapMessage message including territories,continents,message about the checking result
	 */
	public MapMessage validateMap(String mapInfo) {
		
		// Processing continents - Whether the continents are available in the .map file
		boolean isValidContinents = false;
		isValidContinents = processContinents(mapInfo);
		if(isValidContinents) {
			// Processing territories -  Whether the territories are available in the .map file
			boolean isValidTerritories = false;
			isValidTerritories = processTerritories(mapInfo);
			if(isValidTerritories) {
				boolean addAdjacentTerritories = false;
				//Adding adjacent territories
				addAdjacentTerritories = addAdjacentTerritories(mapInfo, territoriesArray);
				if(addAdjacentTerritories) {
					//connecting territories to continents
					boolean valid = false;
					valid = territoriesToContinents();
					if(valid) {
						boolean isMapConnected = false;
						isMapConnected = validateMap(territoriesArray);
						if(isMapConnected) {
							isValidMap = true;
						}				
					}
				}else {
					isValidMap = false;
				}
			}else { // else of isValidTerritories
				isValidMap = false;
			}
		}else {  // else of isValidContinents
			isValidMap = false;
		}
		map.setContinents(continentArray);
		map.setTerritories(territoriesArray);
		MapMessage mapMessage = new MapMessage(map, isValidMap, message);
		
		return mapMessage;
	}
	
	/**
	 * Function to process the content of the .map file and see whether there are Continents available 
	 * If yes, it will get added to ArrayList of Continents
	 * 
	 * @param mapInfo the text from mapTextPane
	 * @return boolean true if the continents are valid
	 */
	public boolean processContinents(String mapInfo) {
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
					continentData[0].trim();
					continentData[1].trim();
					continentData[1] = continentData[1].replaceAll("(\\r|\\n)", "");
					Continent continent1 = new Continent(continentData[0], Integer.parseInt((continentData[1])));
					continentArray.add(continent1);
				}
				valid = true;
			}catch(Exception e) {
				valid = false;
				System.out.println(e.toString());
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
	 * @param mapInfo the text from mapTextPane
	 * @return boolean true if the territories are valid
	 */
	public boolean processTerritories(String mapInfo) {
		boolean isValidTerritories = false;
		if(mapInfo.contains("[Territories]")) {
			try {
				int indexOfTerritories = mapInfo.indexOf("[Territories]");
				String territories = mapInfo.substring(indexOfTerritories);
				String territoryInfo[] = territories.split("\n");
				for(int i=1;i<territoryInfo.length;i++) {
					territoryInfo[i].trim();
					String[] territoryDetails = territoryInfo[i].split(",");
					territoryDetails[0] = territoryDetails[0].trim();
					territoryDetails[3] = territoryDetails[3].trim();
		    		Territory newTerritory = new Territory(territoryDetails[0], territoryDetails[3],0);
		    		territoriesArray.add(newTerritory);
				}
				isValidTerritories = true;
			}catch(Exception e) {
				System.out.println(e.toString());
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
	 * @return boolean true if all territories are assigned to Continents, if there is any territory left return false
	 */
	public boolean territoriesToContinents() {
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
					message.append("The continent, " + continentName + " is not available in the map.\r\n");
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
	 * The method is used to return the index of the continent in the continent array
	 * 
	 * @param obj continentName
	 * @return int index of the object
	 */
	public int indexOfContinent(Object obj) {
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
	 * Returns the Continent from the continentsArray
	 * 
	 * @param continentName continentName
	 * @return Continent continent object
	 */
	public Continent getContinentFromArray(String continentName) {
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
	 * @param territoriesList territoriesList
	 * @return boolean true if the map is valid
	 */
	public boolean validateMap(ArrayList<Territory> territoriesList) {
		boolean isConnected = false;
		int numberOfTerritories = territoriesList.size();
		ArrayList<String> visitedTerritories = new ArrayList<String>();
		ArrayList<String> checkedTerritories = new ArrayList<String>();
		for(int i=0;i<territoriesList.size();i++) {
			Territory territory = (Territory)territoriesList.get(i);
			visitedTerritories.clear();
			checkedTerritories.clear();
			visitedTerritories.add(territory.getName());
			checkedTerritories.add(territory.getName());
			int index = 1;
			while(!checkedTerritories.isEmpty()) {
				Territory newTerritory = getTerritory(checkedTerritories.get(0), territoriesList);
				for(int k = 0;k < newTerritory.getAdjacentTerritories().size();k++) {
					String adjacentTerritory = newTerritory.getAdjacentTerritories().get(k).getName();
					if(visitedTerritories.indexOf(adjacentTerritory) < 0 ) {
						visitedTerritories.add(adjacentTerritory);
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
				message.append("Territory: " + territory.getName() + "\n");
				message.append("*************************************************\n");
				for(int j=0;j<territoriesArray.size();j++) {
					if(!visitedTerritories.contains(territoriesArray.get(j).getName())){
						message.append("It is not connected to:"+territoriesArray.get(j).getName()+"\n");
					}
				}
				message.append("\n");
				message.append("Territories are not well connected\n");
				isConnected = false;
			}
		}
		return isConnected;		
	}
	
	/**
	 * Return territory using the territoryName
	 * 	
	 * @param territoryName territoryName
	 * @param territoriesList territoriesList
	 * @return Territory territory object
	 */
	public Territory getTerritory(String territoryName, ArrayList<Territory> territoriesList) {
		Territory territory = null;
		for(int i = 0;i<territoriesList.size();i++) {
			if(territoryName.equals(territoriesList.get(i).getName())) {
				territory = (Territory)territoriesList.get(i);
			}
		}
		return territory;
	}
	
	/**
	 * Find the continent of a territory
	 * 
	 * @param territory territory object
	 * @return Continent continent object
	 */
	public Continent findContinentOfTerritory(Territory territory) {
		Continent continent = null;
		for(int i=0;i<continentArray.size();i++) {
			if(continentArray.get(i).getTerritories().contains(territory)) {
				continent = continentArray.get(i);
			}
		}
		return continent;
	}
	
	/**
	 * Returns the Territory from the territoriesArray
	 * 
	 * @param territoryName territoryName
	 * @return Territory territory object
	 */
	public Territory getTerritoryFromArray(String territoryName) {
		Territory territory = new Territory();
		for(int i = 0; i < territoriesArray.size();i++) {
			if(territoryName.equals(territoriesArray.get(i).getName())) {
				territory = territoriesArray.get(i);
			}
		}
		return territory;
	}
	
	/**
	 * Add adjacent territories to each territories
	 * 
	 * @param mapInfo String content of file
	 * @param territories ArrayList of territories
	 * @return true/false
	 */
	public boolean addAdjacentTerritories(String mapInfo, ArrayList<Territory> territories) {
		boolean isValidTerritories = false;
		if(mapInfo.contains("[Territories]")) {
			try {
				int indexOfTerritories = mapInfo.indexOf("[Territories]");
				String territoryStrings = mapInfo.substring(indexOfTerritories);
				String territoryInfo[] = territoryStrings.split("\n");
				for(int i=1;i<territoryInfo.length;i++) {
					territoryInfo[i].trim();
					String[] territoryDetails = territoryInfo[i].split(",");
					Territory newTerritory = getTerritory(territoryDetails[0], territories);
		    		ArrayList<Territory> adjacentTerritories = new ArrayList<Territory>();
		    		for(int j=4;j<territoryDetails.length;j++) {
		    			territoryDetails[j] = territoryDetails[j].trim();
		    			Territory adjTerr = getTerritory(territoryDetails[j].trim(), territories);
		    			adjacentTerritories.add(adjTerr);
		    		}
		    		newTerritory.setAdjacentTerritories(adjacentTerritories);
				}
				isValidTerritories = true;
			}catch(Exception e) {
				System.out.println(e.toString());
				message.append("There is some error in the syntax of the territories, Please recheck");
				isValidTerritories = false;
			}			
		}else {
			message.append("Map has no territories");
			isValidTerritories = false;
		}
		return isValidTerritories;
	}

	
	//========================= NEW FUNCTIONS =========================
	public static boolean isInArrayList(ArrayList<Territory> input, Territory member){
        
        for (Territory temp: input){
            if (temp.getName().equals(member.getName())){
                return true;
            }
        }
        return false;
    }

    public boolean checkConnectivity(ArrayList<Territory> conTerritories){
        ArrayList<Territory> mainStack = new ArrayList<Territory>();
        ArrayList<Territory> tempStack = new ArrayList<Territory>();
        
        boolean isConnected = false;
        mainStack.add(conTerritories.get(0));
        tempStack.add(conTerritories.get(0));
        
        while(!isConnected){
            //Taking the first node out
            Territory temp = tempStack.get(0);
            tempStack.remove(temp);

            //Adding Adjacent Territories
            for (Territory territories: temp.getAdjacentTerritories()){
                //if is In that continent
                if (isInArrayList(conTerritories,territories)){
                    //if not already in mainStack
                    if(!isInArrayList(mainStack,territories)){
                        //add it to the mainStack
                        for (Territory conTerritory : conTerritories){
                            if (conTerritory.getName().equals(territories.getName()))
                            {
                                mainStack.add(conTerritory);
                                tempStack.add(conTerritory);
                                break;
                            }
                        }
                    }
                }
            }

            //IsConnected Check
            if (mainStack.size()==conTerritories.size()){
                isConnected=true;
                break;
            }
            //If noMore Nodes Break
            if (tempStack.size()==0){
                break;
            }
            
        }

        return isConnected;
    }

    public boolean checkMapContectivity(Map map){
	
    	boolean isMapConnected = true;
	boolean isConnected = checkConnectivity(map.getTerritories());
    
    if (isConnected){
    		message.append("Map Territories are Connected!\n");
        isConnected = false;
    }else{
    		message.append("Map Territories are NOT Connected!!\n");
        isMapConnected = false;
    }
    return isMapConnected;
    }

    
    public Map setMap(String mapFile) {

        Map map = new Map();

        ArrayList<Continent> con = new ArrayList<Continent>();
        ArrayList<Territory> ter = new ArrayList<Territory>();
        

        //String mapInString = "";
        boolean isContinent = false;
        boolean isTerritory = false;
        Continent tempCon;
        Territory tempTer;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(mapFile));

            String line = reader.readLine();
            mapString += line;

            while (line != null) {
                mapString += line;
                
                if (line.toLowerCase().contains("continents"))
                {isContinent = true;isTerritory=false;}
                if (line.toLowerCase().contains("territories"))
                {isContinent = false;isTerritory=true;}

                if (isContinent){
                String[] tokens = line.split("=");
                    if (tokens.length>1){
                        tempCon = new Continent();
                        tempCon.setName(tokens[0]);
                        tempCon.setNumberOfArmies(Integer.parseInt(tokens[1]));
                        con.add(tempCon);
                    }
                }
                if (isTerritory){
                    String[] tokens = line.split(",");
                    if (tokens.length>3){
                        tempTer = new Territory();
                        
                        tempTer.setName(tokens[0]);
                        tempTer.setContinent(tokens[3]);
                        if (tokens.length>4)
                        {
                            ArrayList<Territory> adTer = new ArrayList<Territory>();
                            for(int i=4;i<tokens.length;i++){
                                Territory tt = new Territory();
                                tt.setName(tokens[i]);
                                adTer.add(tt);
                            }
                            // System.out.println(adTer.size());
                            tempTer.setAdjacentTerritories(adTer);
                            // System.out.println(tempTer.getAdjacentTerritories().size());
                            // adTer.clear();
                        }
                        ter.add(tempTer);
                        // System.out.println(tempTer.getAdjacentTerritories().size());

                        for(int i=0;i<con.size();i++){
                            if (con.get(i).getName().equals(tempTer.getContinent())){
                                con.get(i).addTerritories(tempTer);
                                break;
                            }
                        }
                    }
                }

                line = reader.readLine();
            }
            reader.close();
            
            //==== MAP ====
            map.setContinents(con);
            map.setTerritories(ter);
            //=============

        } catch (IOException e) {
            map = null;
        }
        return map;
    }
    
    
}
