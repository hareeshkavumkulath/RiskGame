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
	 * @return
	 */
	public ArrayList<Player> addTerritories(ArrayList<Player> playerList, ArrayList<Territory> territories) {
		int count = 0;
		int temp = 0;
		int numTerritories = territories.size();
		int numPlayers = playerList.size();
		int[] result = new int[numTerritories]; 
		result = getRandomIndex(numTerritories);
		while(count != numTerritories) {
			int index = result[count];
			if(temp < numPlayers) {
				playerList.get(temp).getOwnedTerritories().add(territories.get(count));
				temp++;
				count++;
			}else {
				temp = 0;
			}			
		}
		System.out.println(playerList.get(0).getOwnedTerritories().size());
		return playerList;
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
	
}
