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
	public static ArrayList<Territory> countriesArray = new ArrayList<Territory>();
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
		
		// Validating continents - Whether the continents are available in the .map file
		boolean isValidContinents = false;
		isValidContinents = processContinents(fileContent.toString());
		
		return null;
	}
	
	
	/**
	 * Function to process the content of the .map file and see whether there is Continents available 
	 * If yes, it will get added to ArrayList
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

}
