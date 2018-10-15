/**
 * 
 */
package com.risk.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.risk.model.Player;
import com.risk.model.Territory;

/**
 * @author Hareesh Kavumkulath
 *
 */
public class GameController {

	/**
	 * @param playerList
	 * @param territories
	 * @return arrayList of Players
	 */
	public ArrayList<Player> territoriesToPlayers(ArrayList<Player> playerList, ArrayList<Territory> territories) {
		int count = 0;
		int temp = 0;
		int numTerritories = territories.size();
		int numPlayers = playerList.size();
		int[] result = new int[numTerritories]; 
		result = getRandomIndex(numTerritories);
		while(count != numTerritories) {
			int index = result[count];
			if(temp < numPlayers) {
				playerList.get(temp).getOwnedTerritories().add(territories.get(index));
				temp++;
				count++;
			}else {
				temp = 0;
			}			
		}
		return playerList;
	}

	/**
	 * @param playerList
	 * @param territories
	 */
	public ArrayList<Territory> playersToTerritories(ArrayList<Player> playerList, ArrayList<Territory> territories) {
		for(int i=0;i<playerList.size();i++) {
			for(int j=0;j<playerList.get(i).getOwnedTerritories().size();j++) {
				int index = territories.indexOf(playerList.get(i).getOwnedTerritories().get(j));
				territories.get(index).setRuler(playerList.get(i));
			}
		}
		return territories;
	}

	/**
	 * @param numTerritories
	 * @param assignedIndexes
	 * @return
	 */
	private int[] getRandomIndex(int numTerritories) {
		Random random = new Random();
		int[] result = new int[numTerritories];  
	    Set<Integer> used = new HashSet<Integer>();  
	      
	    for (int i = 0; i < numTerritories; i++) {  	          
	        int newRandom;  
	        do {  
	            newRandom = random.nextInt(numTerritories);  
	        } while (used.contains(newRandom));  
	        result[i] = newRandom;  
	        used.add(newRandom);  
	    }  
	    return result; 
	}

	/**
	 * @param number
	 * @return number of armies each player get on setup
	 */
	public int getPlayersArmies(int number) {
		if(number == 2) {
			return 40;
		}else if(number == 3) {
			return 35;
		}else if(number == 4) {
			return 30;
		}else if(number == 5) {
			return 25;
		}else {
			return 20;
		}
	}

	/**
	 * @param playerList
	 * @param territories
	 */
	public String assignOneArmyToEachCountry(ArrayList<Player> playerList, ArrayList<Territory> territories) {
		String message = "";
		for(int i = 0;i<playerList.size();i++) {
			for(int j=0;j<playerList.get(i).getOwnedTerritories().size();j++) {
				int currentNumberOfArmies = playerList.get(i).getNumberOfArmies();
				playerList.get(i).getOwnedTerritories().get(j).setNumberOfArmies(1);
				playerList.get(i).setNumberOfArmies(currentNumberOfArmies-1);
				message = message + "Player, " + playerList.get(i).getName() + " Placed an army to the territory " +  playerList.get(i).getOwnedTerritories().get(j).getName() + " " + playerList.get(i).getNumberOfArmies() + "\r\n";
			}
		}
		return message;
	}	
	
	
		
}
