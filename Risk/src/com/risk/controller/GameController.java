package com.risk.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;

import com.risk.model.Card;
import com.risk.model.Continent;
import com.risk.model.Game;
import com.risk.model.Map;
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
	 * @param numArmies 
	 * @return boolean status
	 */
	public boolean addArmyToTerritory(Player player, Territory territory, int numArmies) {
		boolean isAdded = false;
		try {
			int territoryIndex = player.getOwnedTerritories().indexOf(territory);
			int currentNum = player.getOwnedTerritories().get(territoryIndex).getNumberOfArmies();
			int currentNumberOfArmies = player.getNumberOfArmies();
			if(currentNumberOfArmies > 0) {
				player.getOwnedTerritories().get(territoryIndex).setNumberOfArmies(currentNum + numArmies);
				player.setNumberOfArmies(currentNumberOfArmies-numArmies);
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
		if(currentNumArmies > fortifyNum) {
			isValidNumber = true;
		}
		return isValidNumber;
	}

	/**
	 * Fortify with the fortifyNumber
	 * 
	 * @param player Player
	 * @param territoryFrom From Territory
	 * @param territoryTo To Territory
	 * @param fortifyNum number of fortifying armies
	 * @return boolean true if fortify function is completed else false
	 */
	public boolean fortify(Player player, Territory territoryFrom, Territory territoryTo, int fortifyNum) {
		boolean status = false;
		try {
			int fromTerrNumArmies = territoryFrom.getNumberOfArmies();
			int toTerrNumArmies = territoryTo.getNumberOfArmies();
			fromTerrNumArmies = fromTerrNumArmies - fortifyNum;
			toTerrNumArmies = toTerrNumArmies + fortifyNum;
			territoryFrom.setNumberOfArmies(fromTerrNumArmies);
			territoryTo.setNumberOfArmies(toTerrNumArmies);
			player.setFortificationStatus(true);
			status = true;
		}catch(Exception e) {
			status = false;
		}
		return status;
	}

	/**
	 * Get Domination of a player in a continent
	 * 
	 * @param continent Continent
	 * @param player Player
	 * @return double as percentage
	 */
	public double getDomination(Continent continent, Player player) {
		double percentage = 0.0;
		int totalNumTerritories = continent.getTerritories().size();
		int count = 0;
		for(int i=0;i<continent.getTerritories().size();i++) {
			if(continent.getTerritories().get(i).getRuler() == player) {
				count++;
			}
		}
		percentage = (double)(count * 100) / (double)totalNumTerritories;
		return percentage;
	}

	/**
	 * Get Domination of a Player in the Map
	 * 
	 * @param player Player
	 * @param territories Total territories
	 * @return double as percentage
	 */
	public double getDomination(Player player, ArrayList<Territory> territories) {
		double percentage = 0.0;
		int totalNumTerritories = territories.size();
		int count = 0;
		for(int i=0;i<territories.size();i++) {
			if(territories.get(i).getRuler() == player) {
				count++;
			}
		}
		percentage = (double)(count * 100) / (double)totalNumTerritories;
		return percentage;
	}

	/**
	 * Create cards
	 * @param numPlayers 
	 * @param numTerritories 
	 * 
	 * @return ArrayList of Cards
	 */
	public ArrayList<Card> loadCards(int numTerritories, int numPlayers) {
		int eachType = numTerritories / numPlayers;
		if((numTerritories % numPlayers) > 0) {
			eachType++;
		}
		ArrayList<Card> cards = new ArrayList<Card>();
		for(int i=0;i<eachType;i++) {
			Card newCard = new Card("Infantry");
			newCard.addArmy("Infantry");
			cards.add(newCard);
		}
		for(int i=0;i<eachType;i++) {
			Card newCard = new Card("Cavalry");
			newCard.addArmy("Cavalry");
			cards.add(newCard);
		}
		for(int i=0;i<eachType;i++) {
			Card newCard = new Card("Infantry");
			newCard.addArmy("Artillery");
			cards.add(newCard);
		}
		return cards;
	}

	/**
	 * Function to check whether the player has their own continents
	 * 
	 * @param continents ArrayList of continents
	 * @param playerList ArrayList of Players
	 * @return ArrayList<Players> Players after adding the continents
	 */
	public ArrayList<Player> getOwnedContinents(ArrayList<Continent> continents, ArrayList<Player> playerList) {
		for(int i=0;i<continents.size();i++) {
			Player player = getRulerOfContinent(continents.get(i));
			if(player != null) {
				int index = playerList.indexOf(player);
				ArrayList ownedContinents = playerList.get(index).getOwnedContinents();
				ownedContinents.add(continents.get(i));
				playerList.get(index).setOwnedContinents(ownedContinents);
			}
		}
		return playerList;
	}

	/**
	 * @param continent
	 * @return
	 */
	private static Player getRulerOfContinent(Continent continent) {
		ArrayList<Territory> territories = continent.getTerritories();
		Player player = territories.get(0).getRuler();
		boolean status =  true;
		for(int i=1;i<territories.size();i++) {
			Player tempPlayer = territories.get(i).getRuler();
			if(player != tempPlayer) {
				status = false;
			}
		}
		if(status)
			return player;
		else
			return null;
	}

	/**
	 * @param player
	 * @return
	 */
	public int getNumArmiesFromContinents(Player player) {
		int numArmies = 0;
		for(int i=0;i<player.getOwnedContinents().size();i++) {
			numArmies = numArmies + player.getOwnedContinents().get(i).getNumberOfArmies();
		}
		return numArmies;
	}

	/**
	 * @param currentPlayer
	 * @param ruler
	 * @param playerList
	 * @return
	 */
	public ArrayList<Player> updatePlayerList(Player attackerPlayer, Player opponentPlayer, ArrayList<Player> playerList) {
		for(int i=0;i<playerList.size();i++) {
			if(playerList.get(i).getName() == attackerPlayer.getName()) {
				playerList.remove(i);
				playerList.add(i, attackerPlayer);
			}else if(playerList.get(i).getName() == opponentPlayer.getName()) {
				playerList.remove(i);
				playerList.add(i, opponentPlayer);
			}
		}
		for(int i=0;i<playerList.size();i++) {
			if(playerList.get(i).getOwnedTerritories().size() == 0) {
				playerList.remove(i);
			}
		}
		return playerList;
	}

	/**
	 * @param attackerTerr
	 * @param opponentTerr
	 * @param game
	 * @return
	 */
	public Game updateGame(Territory attackerTerr, Territory opponentTerr, Game game) {
		Player opponentRuler = opponentTerr.getRuler();
		int numArmy = 0;
		if(attackerTerr.getNumberOfArmies() == 0) {
			System.out.println("Opponent has won");
			if(opponentTerr.getNumberOfArmies() == 2) {
				numArmy = 1;
			}else if(opponentTerr.getNumberOfArmies() > 2){
				int minLimit = 1;
				int maxLimit = opponentTerr.getNumberOfArmies() - 1;
				String input = JOptionPane.showInputDialog(null, "Move armies(" + minLimit + "-" + maxLimit +")", "Dialog for Input",
				        JOptionPane.WARNING_MESSAGE);
				numArmy = Integer.parseInt(input);
				opponentTerr.setNumberOfArmies(opponentTerr.getNumberOfArmies() - numArmy);
			}
			game = updateTerritoryRuler(attackerTerr, opponentTerr.getRuler(), game, numArmy);
			game = updateTerritoryRuler(opponentTerr, opponentTerr.getRuler(), game, opponentTerr.getNumberOfArmies());
			game = updateAddPlayerList(attackerTerr, opponentTerr.getRuler(), game);
			game = updateRemovePlayerList(attackerTerr, attackerTerr.getRuler(), game);
		}else if(opponentTerr.getNumberOfArmies() == 0) {
			System.out.println("Attacker has won");
			if(attackerTerr.getNumberOfArmies() == 2) {
				numArmy = 1;
			}else if(attackerTerr.getNumberOfArmies() > 2){
				int minLimit = 1;
				int maxLimit = attackerTerr.getNumberOfArmies() - 1;
				String input = JOptionPane.showInputDialog(null, "Move armies(" + minLimit + "-" + maxLimit +")", "Dialog for Input",
				        JOptionPane.WARNING_MESSAGE);
				numArmy = Integer.parseInt(input);
				attackerTerr.setNumberOfArmies(attackerTerr.getNumberOfArmies() - numArmy);
			}
			game = updateTerritoryRuler(opponentTerr, attackerTerr.getRuler(), game, numArmy);
			game = updateTerritoryRuler(attackerTerr, attackerTerr.getRuler(), game, attackerTerr.getNumberOfArmies());
			game = updateAddPlayerList(opponentTerr, attackerTerr.getRuler(), game);
			game = updateRemovePlayerList(opponentTerr, opponentRuler, game);
		}else {
			game = updateTerritoryRuler(attackerTerr, attackerTerr.getRuler(), game, attackerTerr.getNumberOfArmies());
			game = updateTerritoryRuler(opponentTerr, opponentTerr.getRuler(), game, opponentTerr.getNumberOfArmies());
		}
		game = updatePlayerList(game);
		return game;
	}

	/**
	 * @param territory
	 * @param ruler
	 * @param game
	 * @return
	 */
	private Game updateRemovePlayerList(Territory territory, Player ruler, Game game) {
		int removeIndex = game.getPlayers().indexOf(ruler);
		game.getPlayers().get(removeIndex).getOwnedTerritories().remove(territory);
		System.out.println(territory.getName() + " is removed from " + ruler.getName());
		return game;
	}

	/**
	 * @param territory
	 * @param ruler
	 * @param game
	 * @return
	 */
	public Game updateAddPlayerList(Territory territory, Player ruler, Game game) {
		int addIndex = game.getPlayers().indexOf(ruler);
		game.getPlayers().get(addIndex).getOwnedTerritories().add(territory);
		System.out.println(territory.getName() + " is added to " + ruler.getName());
		return game;
	}

	/**
	 * @return
	 */
	private Game updatePlayerList(Game game) {
		for(int i=0;i<game.getPlayers().size();i++) {
			Player currPlayer = game.getPlayers().get(i);
			if(currPlayer.getOwnedTerritories().size() == 0) {
				game.getPlayers().remove(i);
			}
		}
		return game;		
	}

	/**
	 * @param terr
	 * @param ruler
	 */
	private Game updateTerritoryRuler(Territory territory, Player ruler, Game game, int numArmy) {
		int index = game.getMap().getTerritories().indexOf(territory);
		game.getMap().getTerritories().get(index).setRuler(ruler);
		game.getMap().getTerritories().get(index).setNumberOfArmies(numArmy);
		return game;
	}

	/**
	 * @param attackerTerr
	 * @param opponentTerr
	 * @param numAttackerArmies
	 * @param numOpponentArmies
	 * @return
	 */
	public boolean hasEnoughArmies(Territory attackerTerr, Territory opponentTerr, int numAttackerArmies,
			int numOpponentArmies) {
		if(attackerTerr.getNumberOfArmies() <= numAttackerArmies) {
			return false;
		}else if(opponentTerr.getNumberOfArmies() < numOpponentArmies) {
			return false;
		}else {
			return true;
		}
	}

	/**
	 * @param winner
	 * @param cards
	 * @param game
	 * @return
	 */
	public Game getCard(Player winner, ArrayList<Card> cards, Game game) {
		int size = cards.size();
		int indexOfPlayer = game.getPlayers().indexOf(winner);
		int randomIndex = new Random().nextInt(size);
		System.out.println(cards.get(randomIndex).getArmyType());
		game.getPlayers().get(indexOfPlayer).getCards().add(cards.get(randomIndex));
		cards.remove(randomIndex);
		game.setCards(cards);
		return game;
	}
	
}
