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
	/**
	 * The method is used to check whether the player still have the armies to add.
	 * @param playerList
	 * @return true the adding is completed, false is still in processing
	 */
	public boolean isAddingCompleted(ArrayList<Player> playerList) {
		boolean isAddingCompleted = false;
		int totalNum = 0;
		for(int i=0;i<playerList.size();i++) {
			totalNum = totalNum + playerList.get(i).getNumberOfArmies();
		}
		System.out.println("Total Number"+totalNum);
		if(totalNum == 0) {
			isAddingCompleted = true;
		}
		return isAddingCompleted;
	}
	/**
	 * The method is used to add the player's army to the territory he owned.
	 * @param player the player
	 * @param territory the territory is owned by the player
	 * @return
	 */
	public boolean addArmyToTerritory(Player player, Territory territory) {
		boolean isAdded = false;
		try {
			int territoryIndex = player.getOwnedTerritories().indexOf(territory);
			int currentNum = player.getOwnedTerritories().get(territoryIndex).getNumberOfArmies();
			int currentNumberOfArmies = player.getNumberOfArmies();
			if(currentNumberOfArmies > 0) {
				player.getOwnedTerritories().get(territoryIndex).setNumberOfArmies(currentNum+1);
				player.setNumberOfArmies(currentNumberOfArmies-1);
				isAdded = true;
			}			
		}catch(Exception e) {
			isAdded = false;
		}
		return isAdded;		
	}
	/**
	 * The method is used to give the player certain number of armies  
	 * at the start of the reinforcement stage.
	 * @param player the player
	 * @return the number of armies the player can get.
	 */
	public int getNumReinforcements(Player player) {
		int numReinforcements = 3;
		int numTerritories = player.getOwnedTerritories().size();
		numReinforcements = numTerritories/4;
		if(numReinforcements < 3) {
			numReinforcements = 3;
		}
		return numReinforcements;
	}
	/**
	 * The method is used to check whether the fortification is valid.
	 * @param player the player
	 * @return
	 */
	public boolean isValidFortify(Player player) {
		boolean isValidFortify = false;
		if(player.getOwnedTerritories().size() == 1) {
			isValidFortify = false;
		}else {
			for(int i=0;i<player.getOwnedTerritories().size();i++) {
				Territory territory = player.getOwnedTerritories().get(i);
				if(territory.getNumberOfArmies() > 1) {
					isValidFortify = true;
					break;
				}
			}
		}
		return isValidFortify;
	}
	
	public boolean validateFortifyMove(Player player, int selectedIndex) {
		boolean validMove = false;
		if(player.getOwnedTerritories().get(selectedIndex).getNumberOfArmies() > 1) {
			validMove = true;
		}
		return validMove;
	}

	public boolean validateFortifyNumber(Player player, int selectedIndex, int fortifyNum) {
		boolean isValidNumber = false;
		int currentNumArmies = player.getOwnedTerritories().get(selectedIndex).getNumberOfArmies();
		if(currentNumArmies - 1 > fortifyNum) {
			isValidNumber = true;
		}
		return isValidNumber;
	}
}
