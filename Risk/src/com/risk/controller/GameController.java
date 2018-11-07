package com.risk.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;

import com.risk.model.Card;
import com.risk.model.Continent;
import com.risk.model.Game;
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
	 * @param numArmies the number of armies needs to be passed to addArmyToTerritory method to do implementation
	 * @return boolean status if the army to territory has been added
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
	 * Get Domination of a player in a continent
	 * 
	 * @param continent Continent
	 * @param player Player
	 * @return double as percentage
	 */
	public double getDomination(Continent continent, Player player) {
		double percentage = 0.00;
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
		double percentage = 0.00;
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
	 * 
	 * @param numTerritories pass number of territories for adding cards implementation
	 * @return ArrayList of Cards
	 */
	public ArrayList<Card> loadCards(int numTerritories) {
		int eachType = numTerritories / 3;
		if((numTerritories % 3) > 0) {
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
		for(int i=0;i<playerList.size();i++) {
			playerList.get(i).getOwnedContinents().clear();
		}
		for(int i=0;i<continents.size();i++) {
			Player player = getRulerOfContinent(continents.get(i));
			if(player != null) {
				int index = playerList.indexOf(player);
				playerList.get(index).getOwnedContinents().add(continents.get(i));
			}
		}
		return playerList;
	}

	/**
	 * @param continent pass the continent parameter to get the ruler 
	 * @return Player type if the getRulerOfContinent status is successful
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
	 * @param player pass player parameter to get his Number of Armies From Continents
	 * @return int for the number of armies
	 */
	public int getNumArmiesFromContinents(Player player) {
		int numArmies = 0;
		for(int i=0;i<player.getOwnedContinents().size();i++) {
			numArmies = numArmies + player.getOwnedContinents().get(i).getNumberOfArmies();
		}
		return numArmies;
	}

	/**
	 * @param attackerPlayer pass the player to the method and update the information, as attacker
	 * @param opponentPlayer pass the player to the method and update the information, as defender
	 * @param playerList pass the list of the player to update information
	 * @return ArrayList the list of players
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
	 * 
	 * 
	 * @param attackerTerr pass attacker territory to update game status
	 * @param opponentTerr pass opponent territory to update game status
	 * @param game pass game to update its status
	 * @return Game to get the new updated game status
	 */
	public Game updateGame(Territory attackerTerr, Territory opponentTerr, Game game) {
		Player opponentRuler = opponentTerr.getRuler();
		Player attackerRuler = attackerTerr.getRuler();
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
				System.out.println(input);
				numArmy = Integer.parseInt(input);
			}
			opponentTerr.setNumberOfArmies(opponentTerr.getNumberOfArmies() - numArmy);
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
				System.out.println(input);
				numArmy = Integer.parseInt(input);
			}
			attackerTerr.setNumberOfArmies(attackerTerr.getNumberOfArmies() - numArmy);
			if(opponentRuler.getOwnedTerritories().size() == 0) {
				ArrayList<Card> cards = opponentRuler.getCards();
				if(cards != null) {
					for(int i=0;i<cards.size();i++) {
						attackerRuler.getCards().add(cards.get(i));
					}
				}
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
	 * This method updates the new removed player list
	 * 
	 * @param territory pass territory to update the player list
	 * @param ruler pass ruler to update the defender info
	 * @param game pass game to update the player list 
	 * @return Game to update PlayerList info
	 */
	private Game updateRemovePlayerList(Territory territory, Player ruler, Game game) {
		int removeIndex = game.getPlayers().indexOf(ruler);
		game.getPlayers().get(removeIndex).getOwnedTerritories().remove(territory);
		System.out.println(territory.getName() + " is removed from " + ruler.getName());
		return game;
	}

	/**
	 * This method updates the new added player list
	 * 
	 * @param territory pass parameter territory to update player list
	 * @param ruler pass parameter ruler to update player list
	 * @param game pass parameter game to update new added player list
	 * @return game type to update new added player list
	 */
	public Game updateAddPlayerList(Territory territory, Player ruler, Game game) {
		int addIndex = game.getPlayers().indexOf(ruler);
		game.getPlayers().get(addIndex).getOwnedTerritories().add(territory);
		System.out.println(territory.getName() + " is added to " + ruler.getName());
		return game;
	}

	/**
	 * Update player list - If any player who don't have army it will get deleted from the list
	 * 
	 * @param game pass game parameter to update player list for this private method
	 * @return game type and receive the new updated player list info
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
	 * Update ruler of the territory
	 * 
	 * @param territory pass territory to update territory ruler for this private method
	 * @param ruler pass ruler to update territory ruler for this private method
	 * @param game pass game parameter to update territory ruler for this private method
	 * @param numArmy pass the number of armies to update territory ruler for this private method
	 * @return game type and receive the new updated territory ruler info
	 */
	private Game updateTerritoryRuler(Territory territory, Player ruler, Game game, int numArmy) {
		int index = game.getMap().getTerritories().indexOf(territory);
		game.getMap().getTerritories().get(index).setRuler(ruler);
		game.getMap().getTerritories().get(index).setNumberOfArmies(numArmy);
		return game;
	}

	/**
	 * Check for enough armies
	 * 
	 * @param attackerTerr pass attacker territory to check if the player has enough armies
	 * @param opponentTerr pass opponent territory to check if the player has enough armies
	 * @param numAttackerArmies pass attacker armies number to check if the player has enough armies 
	 * @param numOpponentArmies pass opponent armies to check if the player has enough armies
	 * @return boolean the status if player has enough armies
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
	 * This addCard method implements the adding card step
	 * 
	 * @param winner pass the winner player
	 * @param cards pass the cards list
	 * @param game pass the game to add card information
	 * @return Game type for adding card procedure
	 */
	public Game addCard(Player winner, ArrayList<Card> cards, Game game) {
		int size = cards.size();
		int indexOfPlayer = game.getPlayers().indexOf(winner);
		int randomIndex = new Random().nextInt(size);
		game.getPlayers().get(indexOfPlayer).getCards().add(cards.get(randomIndex));
		cards.remove(randomIndex);
		game.setCards(cards);
		return game;
	}

	/**
	 * Turn In Cards - Add armies based on the attempt
	 * 
	 * @param currentPlayer Current Player
	 * @param selectedValuesList list of selected values
	 * @return Player currentPlayer for setting his cards
	 */
	public Player turnInCards(Player currentPlayer, List<String> selectedValuesList) {
		int turn = currentPlayer.getTurnInCards();
		int numberOfArmies = getNumberOfArmies(turn);
		currentPlayer.setNumberOfArmies(numberOfArmies);
		//Remove cards
		for(String param : selectedValuesList) {
			for(int i=0;i<currentPlayer.getCards().size();i++) {
				if(param.equals(currentPlayer.getCards().get(i).getArmyType())) {
					currentPlayer.getCards().remove(i);
					break;
				}
			}
		}
		turn++;
		currentPlayer.setTurnInCards(turn);
		return currentPlayer;
	}

	/**
	 * Returns number of armies based on the number of turning in attempts
	 * 
	 * @param turn number of turns
	 * @return int number of armies
	 */
	public int getNumberOfArmies(int turn) {
		if(turn == 1) {
			return 4;
		}else if(turn > 1 && turn <= 6) {
			return (4 + (turn -1) * 2);
		}else if(turn == 6) {
			return 15;
		}else {
			return (15 + (turn-6) * 5);
		}
	}

	/**
	 * Get number of attacker armies 
	 * 
	 * @param attackerTerr pass attacker territory parameter to to get the attacker armies
	 * @return int the number of armies 
	 */
	public int getNumAttackerArmies(Territory attackerTerr) {
		int numArmies = attackerTerr.getNumberOfArmies();
		if(numArmies > 3) {
			return 3;
		}else if(numArmies == 2){
			return 1;
		}else {
			return 2;
		}
	}

	/**
	 * Get the number of opponent armies 
	 * 
	 * @param opponentTerr pass parameter of the opponent territory to get his number of armies
	 * @param numAttackerArmies pass parameter of the number of attacker armies to get the defender number of armies
	 * @return
	 */
	public int getNumOpponentArmies(Territory opponentTerr, int numAttackerArmies) {
		int numArmies = opponentTerr.getNumberOfArmies();
		if(numAttackerArmies == 3 && numArmies > 1) {
			return 2;
		}else {
			return 1;
		}
	}
	
}
