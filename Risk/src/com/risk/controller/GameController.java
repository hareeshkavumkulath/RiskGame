package com.risk.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.risk.model.Player;
import com.risk.model.Territory;

/**
 * Controller class for the game setup, players information and territories information
 *
 * @author Hareesh Kavumkulath
 * @version 1.2
 */
public class GameController {

	/**
	 * Distribute players into corresponding territories
	 *
	 * @param playerList ArrayList of players
	 * @param territories ArrayList of territories
	 * @return ArrayList arrayList of Players
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
	 * Function to assign players to every territories
	 * 
	 * @param playerList ArrayList of Players
	 * @param territories ArrayList of territories
	 * @return ArrayList territory list After adding player/ruler to each territory
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
	 * Function to random number from given limit
	 * 
	 * @param numTerritories limit 
	 * @return int[] random number in the limit 
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
	 * Get number of armies each players get based on the Risk rule
	 * 
	 * @param number number of players in the game
	 * @return int number of armies each player get on setup
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
	 * Automatically assign one army to each territory of every player
	 * 
	 * @param playerList ArrayList of players
	 * @param territories ArrayList of territories
	 * @return string status message
	 */
	public String assignOneArmyToEachTerritory(ArrayList<Player> playerList, ArrayList<Territory> territories) {
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
	 * 
	 * @param playerList ArrayList of players
	 * @return boolean true the adding is completed, false is still in processing
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
	 * @return boolean status
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
	 * The method is used to give the player certain number of armies at the start of the reinforcement stage.
	 * 
	 * @param player the player
	 * @return int number of armies the player can get.
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
	 * @return boolean true if the fortification is valid for the player else false
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
	
	/**
	 * Check the player has more than one number of army
	 * 
	 * @param player player who do the fortify
	 * @param selectedIndex Index of the fortify from territory
	 * @return boolean returns true if the move is valid
	 */
	public boolean validateFortifyMove(Player player, int selectedIndex) {
		boolean validMove = false;
		if(player.getOwnedTerritories().get(selectedIndex).getNumberOfArmies() > 1) {
			validMove = true;
		}
		return validMove;
	}

	/**
	 * Check the player has enough number of armies to fortify 
	 * 
	 * @param player Player
	 * @param selectedIndex Index of selected From Territory
	 * @param fortifyNum Number of armies wants to move
	 * @return boolean true if the number is less than the number of armies in the territory
	 */
	public boolean validateFortifyNumber(Player player, int selectedIndex, int fortifyNum) {
		boolean isValidNumber = false;
		int currentNumArmies = player.getOwnedTerritories().get(selectedIndex).getNumberOfArmies();
		if(currentNumArmies - 1 > fortifyNum) {
			isValidNumber = true;
		}
		return isValidNumber;
	}

	/**
	 * Fortify with the fortifyNumber
	 * 
	 * @param player Player
	 * @param fromTerritoryIndex Index of selected From Territory
	 * @param toTerritoryIndex Index of selected To Territory
	 * @param fortifyNum number of fortifying armies
	 * @return boolean true if fortify function is completed else false
	 */
	public boolean fortify(Player player, int fromTerritoryIndex, int toTerritoryIndex, int fortifyNum) {
		boolean status = false;
		try {
			int fromTerrNumArmies = player.getOwnedTerritories().get(fromTerritoryIndex).getNumberOfArmies();
			int toTerrNumArmies = player.getOwnedTerritories().get(toTerritoryIndex).getNumberOfArmies();
			fromTerrNumArmies = fromTerrNumArmies - fortifyNum;
			toTerrNumArmies = toTerrNumArmies + fortifyNum;
			player.getOwnedTerritories().get(fromTerritoryIndex).setNumberOfArmies(fromTerrNumArmies);
			player.getOwnedTerritories().get(toTerritoryIndex).setNumberOfArmies(toTerrNumArmies);
			player.setFortificationStatus(true);
			status = true;
		}catch(Exception e) {
			status = false;
		}
		return status;
	}	
}
