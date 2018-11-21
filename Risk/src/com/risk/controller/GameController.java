package com.risk.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.risk.model.AttackStatus;
import com.risk.model.Card;
import com.risk.model.Continent;
import com.risk.model.Game;
import com.risk.model.GameInstructions;
import com.risk.model.Player;
import com.risk.model.Territory;
import com.risk.view.StartWindow;

/**
 * Controller class for the game setup, players information and territories information
 *
 * @author Hareesh Kavumkulath
 * @version 1.2
 */
public class GameController {
	
	/**
	 * Logger object setup for the log file
	 */
	static Logger logger = Logger.getLogger(StartWindow.class.getName());
	
	@SuppressWarnings("javadoc")
	public Game game;
	@SuppressWarnings("javadoc")
	public GameInstructions gameInstructions;

	/**
	 * GameController Constructor
	 * 
	 * @param game Game object
	 * @param gameInstructions GameInstructions Message
	 */
	public GameController(Game game, GameInstructions gameInstructions) {
		this.game = game;
		this.gameInstructions = gameInstructions;
	}

	/**
	 * Empty Constructor
	 */
	public GameController() {
	}

	/**
	 * Getter for game
	 * 
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Setter for game
	 * 
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Getter for game instructions
	 * 
	 * @return the gameInstructions
	 */
	public GameInstructions getGameInstructions() {
		return this.gameInstructions;
	}

	/**
	 * Setter for game instructions
	 * 
	 * @param gameInstructions the gameInstructions to set
	 */
	public void setGameInstructions(GameInstructions gameInstructions) {
		this.gameInstructions = gameInstructions;
	}
	
	/**
	 * Create cards
	 * 
	 * @param numTerritories pass number of territories for adding cards implementation
	 * @return ArrayList of Cards
	 */
	public ArrayList<Card> loadCards(int numTerritories) {
		logger.log(Level.INFO, "");
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
			Card newCard = new Card("Artillery");
			newCard.addArmy("Artillery");
			cards.add(newCard);
		}
		logger.log(Level.INFO, cards.size() + " cards are loaded");
		return cards;
	}
	
	/**
	 * Assign all territories to players one by one
	 * 
	 * @return Player currentPlayer
	 */
	public Player assignTerritories() {
		logger.log(Level.INFO, "");
		ArrayList<Player> players = game.getPlayers();
		ArrayList<Territory> territories = game.getMap().getTerritories();
		int currentIndex = 0;
		@SuppressWarnings("unchecked")
		ArrayList<Territory> tempTerritories = (ArrayList<Territory>) territories.clone();
		while(tempTerritories.size() > 0) {
			Player currentPlayer = players.get(currentIndex);
			int randomNumber = getRandomNumber(tempTerritories.size());
			currentPlayer.getOwnedTerritories().add(tempTerritories.get(randomNumber));
			tempTerritories.get(randomNumber).setRuler(currentPlayer);
			addArmyToTerritory(tempTerritories.get(randomNumber), 1);
			currentPlayer.setNumberOfArmies(currentPlayer.getNumberOfArmies() - 1);
			logger.log(Level.INFO, currentPlayer.getName() + "(" + currentPlayer.getNumberOfArmies() + ")" + " has placed an army in " + tempTerritories.get(randomNumber).getName());
			gameInstructions.appendInstructions(currentPlayer.getName() + " has placed an army in " + tempTerritories.get(randomNumber).getName() + "\r\n");
			tempTerritories.remove(randomNumber);
			if(currentIndex == (players.size() - 1))
				currentIndex = 0;
			else
				currentIndex++;
		}
		return players.get(currentIndex);
	}
	
	/**
	 * Add army to territory
	 * 
	 * @param territory add army territory
	 * @param num number of armies
	 */
	public void addArmyToTerritory(Territory territory, int num) {
		logger.log(Level.INFO, "");
		int numArmies = territory.getNumberOfArmies();
		territory.setNumberOfArmies(numArmies + num);
	}
	
	/**
	 * Add army to territory
	 * 
	 * @param player Player
	 * @param territory add army territory
	 * @param numArmies number of armies
	 */
	public void addArmyToTerritory(Player player, Territory territory, int numArmies) {
		logger.log(Level.INFO, "inside addArmyToTerritory function");
		int territoryIndex = player.getOwnedTerritories().indexOf(territory);
		int currentNum = player.getOwnedTerritories().get(territoryIndex).getNumberOfArmies();
		int currentNumberOfArmies = player.getNumberOfArmies();
		if(currentNumberOfArmies > 0) {
			player.getOwnedTerritories().get(territoryIndex).setNumberOfArmies(currentNum + numArmies);
			player.setNumberOfArmies(currentNumberOfArmies-numArmies);
			logger.log(Level.INFO, player.getName() + "(" + player.getNumberOfArmies() + ")" + " has placed " + numArmies + " armies in " + territory.getName());
			gameInstructions.setInstructions(player.getName() + " has placed " + numArmies + " armies in " + territory.getName() + "\n");
		}
		if(player.getNumberOfArmies() == 0 && player.getPhase().equals("ADD")) {
			logger.log(Level.INFO, "Adding Armies completed. Move to Reinforcement Phase");
			player.setPhase("REINFORCEMENT");
		}else if(player.getNumberOfArmies() == 0 && player.getPhase().equals("REINFORCEMENT")) {
			logger.log(Level.INFO, "Adding Armies during Reinforcement completed. Move to Attack Phase");
			player.setPhase("ATTACK");
		}
	}
	
	/**
	 * Add army to random territory
	 * 
	 * @param currentPlayer Player current player
	 */
	public void addArmyRandom(Player currentPlayer) {
		logger.log(Level.INFO, "Inside addArmyRandom function");
		int randomNumber = getRandomNumber(currentPlayer.getOwnedTerritories().size());
		addArmyToTerritory(currentPlayer, currentPlayer.getOwnedTerritories().get(randomNumber), 1);
	}
	
	/**
	 * The method is used to give the player certain number of armies at the start of the reinforcement stage.
	 * 
	 * @param player the player
	 * @return integer number of reinforcement armies
	 */
	public int getNumReinforcements(Player player) {
		logger.log(Level.INFO, "Inside addArmyRandom function");
		int numReinforcements = 3;
		int numTerritories = player.getOwnedTerritories().size();
		numReinforcements = numTerritories/4;
		if(numReinforcements < 3) {
			numReinforcements = 3;
		}
		int numArmiesFromContinents = getNumArmiesFromContinents(player);
		int playerNumArmies = player.getNumberOfArmies();
		int reinforcementArmy = numReinforcements + numArmiesFromContinents + playerNumArmies;
		return reinforcementArmy;
	}
	
	/**
	 * This method gets the number of armies from continents
	 * 
	 * @param player pass player parameter to get his Number of Armies From Continents
	 * @return int for the number of armies
	 */
	public int getNumArmiesFromContinents(Player player) {
		logger.log(Level.INFO, "");
		int numArmies = 0;
		for(int i=0;i<player.getOwnedContinents().size();i++) {
			numArmies = numArmies + player.getOwnedContinents().get(i).getNumberOfArmies();
		}
		return numArmies;
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
	public boolean hasEnoughArmies(Territory attackerTerr, Territory opponentTerr, int numAttackerArmies, int numOpponentArmies) {
		logger.log(Level.INFO, "");
		if(attackerTerr.getNumberOfArmies() <= numAttackerArmies) {
			return false;
		}else if(opponentTerr.getNumberOfArmies() < numOpponentArmies) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * Get number of attacker armies 
	 * 
	 * @param attackerTerr pass attacker territory parameter to to get the attacker armies
	 * @return int the number of armies 
	 */
	public int getNumAttackerArmies(Territory attackerTerr) {
		logger.log(Level.INFO, "");
		int retNumArmies = 0;
		int numArmies = attackerTerr.getNumberOfArmies();
		if(numArmies > 3) {
			retNumArmies = 3;
		}else if(numArmies == 2){
			retNumArmies = 1;
		}else {
			retNumArmies = 2;
		}
		logger.log(Level.INFO, "Attacker number of armies:" + retNumArmies);
		return retNumArmies;
	}
	
	/**
	 * Get the number of opponent armies for All out mode
	 * 
	 * @param opponentTerr pass parameter of the opponent territory to get his number of armies
	 * @param numAttackerArmies pass parameter of the number of attacker armies to get the defender number of armies
	 * @return int number of opponent Armies
	 */
	public int getNumOpponentArmies(Territory opponentTerr, int numAttackerArmies) {
		logger.log(Level.INFO, "");
		int retNumArmies = 0;
		int numArmies = opponentTerr.getNumberOfArmies();
		if(numAttackerArmies == 3 && numArmies > 1) {
			retNumArmies = 2;
		}else {
			retNumArmies = 1;
		}
		logger.log(Level.INFO, "Opponent number of armies:" + retNumArmies);
		return retNumArmies;
	}
	
	/**
	 * This method updates the game status including attacker, number of armies and rulers
	 * 
	 * @param attackerTerr pass attacker territory to update game status
	 * @param opponentTerr pass opponent territory to update game status
	 * @param game pass game to update its status
	 * @return Game to get the new updated game status
	 */
	public void updateGame(Territory attackerTerr, Territory opponentTerr) {
		logger.log(Level.INFO, "");
		Player opponentRuler = opponentTerr.getRuler();
		Player attackerRuler = attackerTerr.getRuler();
		int numArmy = 0;
		if(opponentTerr.getNumberOfArmies() == 0) {
			if(attackerTerr.getNumberOfArmies() == 2) {
				numArmy = 1;
			}else if(attackerTerr.getNumberOfArmies() > 2){
				int minLimit = 1;
				int maxLimit = attackerTerr.getNumberOfArmies() - 1;
				numArmy = 0;
				if(attackerRuler.isComputer) {
					Random random = new Random();
					numArmy = random.nextInt(maxLimit) + 1;
					//numArmy = 1;
				}else {
					while(numArmy == 0 || numArmy < minLimit || numArmy > maxLimit) {
						try {
							String input = JOptionPane.showInputDialog(null, "Move armies(" + minLimit + "-" + maxLimit +")", "Dialog for Input",
									JOptionPane.WARNING_MESSAGE);
							numArmy = Integer.parseInt(input);
						}catch(Exception e) {
							numArmy = 0;
						}
					}
				}
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
			updateTerritoryRuler(opponentTerr, attackerTerr.getRuler(), numArmy);
			updateTerritoryRuler(attackerTerr, attackerTerr.getRuler(), attackerTerr.getNumberOfArmies());
			updateAddPlayerList(opponentTerr, attackerTerr.getRuler());
			updateRemovePlayerList(opponentTerr, opponentRuler);
		}else {
			updateTerritoryRuler(attackerTerr, attackerTerr.getRuler(), attackerTerr.getNumberOfArmies());
			updateTerritoryRuler(opponentTerr, opponentTerr.getRuler(), opponentTerr.getNumberOfArmies());
		}
		updatePlayerList();
	}
	
	/**
	 * This method updates the new added player list
	 * 
	 * @param territory pass parameter territory to update player list
	 * @param ruler pass parameter ruler to update player list
	 * @param game pass parameter game to update new added player list
	 * @return game type to update new added player list
	 */
	public void updateAddPlayerList(Territory territory, Player ruler) {
		logger.log(Level.INFO, "");
		int addIndex = game.getPlayers().indexOf(ruler);
		game.getPlayers().get(addIndex).getOwnedTerritories().add(territory);
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
	public void updateTerritoryRuler(Territory territory, Player ruler, int numArmy) {
		logger.log(Level.INFO, "");
		int index = game.getMap().getTerritories().indexOf(territory);
		game.getMap().getTerritories().get(index).setRuler(ruler);
		game.getMap().getTerritories().get(index).setNumberOfArmies(numArmy);
	}
	
	/**
	 * This method updates the new removed player list
	 * 
	 * @param territory pass territory to update the player list
	 * @param ruler pass ruler to update the defender info
	 * @param game pass game to update the player list 
	 * @return Game to update PlayerList info
	 */
	public Game updateRemovePlayerList(Territory territory, Player ruler) {
		logger.log(Level.INFO, "");
		int removeIndex = game.getPlayers().indexOf(ruler);
		game.getPlayers().get(removeIndex).getOwnedTerritories().remove(territory);
		System.out.println(territory.getName() + " is removed from " + ruler.getName());
		return game;
	}
	
	/**
	 * Update player list - If any player who don't have army it will get deleted from the list
	 * 
	 * @param game pass game parameter to update player list for this private method
	 * @return game type and receive the new updated player list info
	 */
	public void updatePlayerList() {
		logger.log(Level.INFO, "");
		for(int i=0;i<game.getPlayers().size();i++) {
			Player currPlayer = game.getPlayers().get(i);
			if(currPlayer.getOwnedTerritories().size() == 0) {
				game.getPlayers().remove(i);
			}
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
		logger.log(Level.INFO, "");
		int size = cards.size();
		int indexOfPlayer = game.getPlayers().indexOf(winner);
		Random rand = new Random();
		int randomIndex = rand.nextInt(size);
		game.getPlayers().get(indexOfPlayer).getCards().add(cards.get(randomIndex));
		cards.remove(randomIndex);
		game.setCards(cards);
		return game;
	}
	
	/**
	 * The method is used to check whether the fortification is valid
	 * 
	 * @param player the player
	 * @return boolean true if the fortification is valid for the player else false
	 */
	public boolean isValidFortify(Player player) {
		logger.log(Level.INFO, "");
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
	 * Get strong territory for aggressive player
	 * 
	 * @param currentPlayer
	 * @return territory strong territory
	 */
	public Territory getStrongTerritory(Player currentPlayer) {
		logger.log(Level.INFO, "");	
		@SuppressWarnings("unchecked")
		ArrayList<Territory> tempTerritories = (ArrayList<Territory>) currentPlayer.getOwnedTerritories().clone();
		Territory strongTerritory = null;
		while(strongTerritory == null && tempTerritories.size() != 0) {
			strongTerritory = tempTerritories.get(0);
			for(int i=1;i<tempTerritories.size();i++) {
				if(strongTerritory.getNumberOfArmies() < tempTerritories.get(i).getNumberOfArmies()) {
					strongTerritory = tempTerritories.get(i);
				}
			}
			tempTerritories.remove(tempTerritories.indexOf(strongTerritory));
			if(strongTerritory.getNumberOfArmies() > 0) {
				boolean hasOpponent = false;
				for(int i=0;i<strongTerritory.getAdjacentTerritories().size();i++) {
					if(currentPlayer != strongTerritory.getAdjacentTerritories().get(i).getRuler()) {
						hasOpponent = true;
					}
				}
				if(!hasOpponent) {
					strongTerritory = null;
				}
			}else {
				strongTerritory = null;
			}
		}
		return strongTerritory;
	}
	
	/**
	 * Get weak territory for benevolent player
	 * 
	 * @param currentPlayer
	 * @return territory weak territory
	 */
	public Territory getWeakTerritory(Player currentPlayer) {
		logger.log(Level.INFO, "");
		@SuppressWarnings("unchecked")
		ArrayList<Territory> tempTerritories = (ArrayList<Territory>) currentPlayer.getOwnedTerritories().clone();
		Territory weakTerritory = null;
		weakTerritory = tempTerritories.get(0);
		for(int i=1;i<tempTerritories.size();i++) {
			if(weakTerritory.getNumberOfArmies() > tempTerritories.get(i).getNumberOfArmies()) {
				weakTerritory = tempTerritories.get(i);
			}
		}
		return weakTerritory;
	}
	
	/**
	 * Get attaker territory
	 * 
	 * @param currentPlayer Player current player
	 * @return territory attacker territory
	 */
	public Territory getAttacker(Player currentPlayer) {
		logger.log(Level.INFO, "");
		@SuppressWarnings("unchecked")
		ArrayList<Territory> tempTerritories = (ArrayList<Territory>) currentPlayer.getOwnedTerritories().clone();
		Territory attacker = null;
		while(attacker == null && tempTerritories.size() != 0) {
			attacker = tempTerritories.get(0);
			for(int i=1;i<tempTerritories.size();i++) {
				if(attacker.getNumberOfArmies() < tempTerritories.get(i).getNumberOfArmies()) {
					attacker = tempTerritories.get(i);
				}
			}
			tempTerritories.remove(tempTerritories.indexOf(attacker));
			if(attacker.getNumberOfArmies() > 1) {
				boolean hasOpponent = false;
				for(int i=0;i<attacker.getAdjacentTerritories().size();i++) {
					if(currentPlayer != attacker.getAdjacentTerritories().get(i).getRuler()) {
						hasOpponent = true;
					}
				}
				if(!hasOpponent) {
					attacker = null;
				}
			}else {
				attacker = null;
			}
		}
		logger.log(Level.INFO, "Attacker Territory for " + currentPlayer.getName() + " is " + attacker.getName());
		return attacker;
	}
	
	/**
	 * Get opponent territory
	 * 
	 * @param currentPlayer Player current player
	 * @param attacker Territory attacker territory
	 * @return Territory opponent territory
	 */
	public Territory getOpponent(Player currentPlayer, Territory attacker) {
		logger.log(Level.INFO, "");
		Territory opponent = new Territory();
		int number = 0;
		ArrayList<Territory> oppponentTerritories = new ArrayList<Territory>();
		for(int i=0;i<attacker.getAdjacentTerritories().size();i++) {
			if(attacker.getAdjacentTerritories().get(i).getRuler() != currentPlayer) {
				oppponentTerritories.add(attacker.getAdjacentTerritories().get(i));
				number++;
			}
		}
		if(number == 0) {
			logger.log(Level.INFO, currentPlayer.getName() + " has no opponent for " + attacker.getName());
			return null;
		}else {
			opponent = oppponentTerritories.get(0);
			for(int i=1;i<oppponentTerritories.size();i++) {
				if(opponent.getNumberOfArmies() > oppponentTerritories.get(i).getNumberOfArmies()) {
					opponent = oppponentTerritories.get(i);
				}
			}
			logger.log(Level.INFO, "Opponent territory for " + attacker.getName() + " is " + opponent.getName());
			return opponent;
		}
	}
	
	/**
	 * Attack method implements the attack phase and define the variables, number of armies, show status messages 
	 * 
	 * @param attackerTerr pass the attacker territory into attack function
	 * @param opponentTerr pass the opponent territory into the attack method
	 * @param numAttackerArmies pass the number of attacker armies into attack and update it
	 * @param numOpponentArmies pass the number of opponent armies into attack and update it
	 * @return AttackStatus of the current attack status
	 */
	public AttackStatus attack(Territory attackerTerr, Territory opponentTerr, int numAttackerArmies,int numOpponentArmies) {
		logger.log(Level.INFO, "");
		StringBuffer message = new StringBuffer();
		
		AttackStatus status = new AttackStatus();
		
		int numArmyAttacker = attackerTerr.getNumberOfArmies();
		int numArmyOpponent = opponentTerr.getNumberOfArmies();
		
		Integer[] firstDices = new Integer[numAttackerArmies];
		Integer[] secondDices = new Integer[numOpponentArmies];
		
		for(int i=0;i<numAttackerArmies;i++) {
			firstDices[i] = new Random().nextInt(6) + 1;
		}
		
		for(int i=0;i<numOpponentArmies;i++) {
			secondDices[i] = new Random().nextInt(6) + 1;
		}
		
		Arrays.sort(firstDices, Collections.reverseOrder());
		Arrays.sort(secondDices, Collections.reverseOrder());
		
		logger.log(Level.INFO, attackerTerr.getRuler().getName() + " has "+ numAttackerArmies +" dices and the results are ");
		message.append(attackerTerr.getRuler().getName() + " has "+ numAttackerArmies +" dices and the results are\n");
		for(int i=0;i<numAttackerArmies;i++) {
			logger.log(Level.INFO, ""+firstDices[i]);
			message.append(firstDices[i] + "\n");
		}
		
		logger.log(Level.INFO, attackerTerr.getRuler().getName() + " has "+ numAttackerArmies +" dices and the results are ");
		message.append(opponentTerr.getRuler().getName() + " has " + numOpponentArmies + " dices and the results are\n");
		for(int i=0;i<numOpponentArmies;i++) {
			logger.log(Level.INFO, ""+secondDices[i]);
			message.append(secondDices[i] + "\n");
		}
		
		for(int i=0;i<numOpponentArmies;i++) {
			boolean win = compareDices(firstDices[i], secondDices[i]);
			logger.log(Level.INFO, "Comparing " + firstDices[i] + "," + secondDices[i]);
			message.append("Comparing " + firstDices[i] + "," + secondDices[i] + "\n");
			if(win) {
				logger.log(Level.INFO, opponentTerr.getRuler().getName() + " lost an army.");
				message.append(opponentTerr.getRuler().getName() + " lost an army.\n");
				numArmyOpponent--;
			}else {
				logger.log(Level.INFO, attackerTerr.getRuler().getName() + " lost an army.");
				message.append(attackerTerr.getRuler().getName() + " lost an army.\n");
				numArmyAttacker--;
			}
		}
		
		attackerTerr.setNumberOfArmies(numArmyAttacker);
		opponentTerr.setNumberOfArmies(numArmyOpponent);
		
		logger.log(Level.INFO, attackerTerr.getRuler().getName() + " has balance armies in " + attackerTerr.getName() + " is " + numArmyAttacker);
		logger.log(Level.INFO, opponentTerr.getRuler().getName() + " has balance armies in " + opponentTerr.getName() + " is " + numArmyOpponent);
		
		if(numArmyOpponent <= 0) {
			status.setHasWon(true);
			status.setWinner(attackerTerr.getRuler());
			logger.log(Level.INFO, attackerTerr.getRuler().getName() + " won and balance armies " + (numArmyAttacker));
			message.append(attackerTerr.getRuler().getName() + " won and balance armies " + (numArmyAttacker) + "\n");
		}else {
			logger.log(Level.INFO, "Nobody wins");
			status.setHasWon(false);
		}
		status.setStatusMessage(message);
		return status;
		
	}
	
	/**
	 * Get number of reinforcement armies for cheater player
	 * 
	 * @param currentPlayer Player current player
	 * @return integer number of reinforcement armies
	 */
	public int calculateReinforcementArmiesForCheater(Player currentPlayer) {
		logger.log(Level.INFO, "");
		int totalNum = 0;
		for(int i=0;i<currentPlayer.getOwnedTerritories().size();i++) {
			totalNum = totalNum + currentPlayer.getOwnedTerritories().get(i).getNumberOfArmies();
		}
		return totalNum;
	}

	/**
	 * Get random attacker for random player
	 * 
	 * @param currentPlayer Player current player
	 * @return Territory random territory which can attack
	 */
	public Territory getRandomAttacker(Player currentPlayer) {
		logger.log(Level.INFO, "");
		@SuppressWarnings("unchecked")
		ArrayList<Territory> tempTerritories = (ArrayList<Territory>) currentPlayer.getOwnedTerritories().clone();
		Territory attacker = null;
		while(attacker == null && tempTerritories.size() != 0) {
			attacker = tempTerritories.get(getRandomNumber(tempTerritories.size()));
			tempTerritories.remove(tempTerritories.indexOf(attacker));
			if(attacker.getNumberOfArmies() > 1) {
				boolean hasOpponent = false;
				for(int i=0;i<attacker.getAdjacentTerritories().size();i++) {
					if(currentPlayer != attacker.getAdjacentTerritories().get(i).getRuler()) {
						hasOpponent = true;
					}
				}
				if(!hasOpponent) {
					attacker = null;
				}
			}else {
				attacker = null;
			}
		}
		return attacker;
	}
	
	/**
	 * Get safe territory for fortification
	 * 
	 * @param currentPlayer Player current player
	 * @return territory safe territory which can fortify
	 */
	public Territory getSafeTerritory(Player currentPlayer) {
		logger.log(Level.INFO, "");
		@SuppressWarnings("unchecked")
		ArrayList<Territory> tempTerritories = (ArrayList<Territory>) currentPlayer.getOwnedTerritories().clone();
		Territory safeTerritory = null;
		while(safeTerritory == null && tempTerritories.size() != 0) {
			safeTerritory = tempTerritories.get(0);
			for(int i=1;i<tempTerritories.size();i++) {
				if(safeTerritory.getNumberOfArmies() < tempTerritories.get(i).getNumberOfArmies()) {
					safeTerritory = tempTerritories.get(i);
				}
			}
			tempTerritories.remove(tempTerritories.indexOf(safeTerritory));
			if(safeTerritory.getNumberOfArmies() > 1) {
				boolean hasOpponent = false;
				for(int i=0;i<safeTerritory.getAdjacentTerritories().size();i++) {
					if(currentPlayer != safeTerritory.getAdjacentTerritories().get(i).getRuler()) {
						hasOpponent = true;
					}
				}
				if(hasOpponent) {
					safeTerritory = null;
				}
			}else {
				safeTerritory = null;
			}
		}
		if(safeTerritory != null) {
			logger.log(Level.INFO, currentPlayer.getName() + "'s safest territory is " + safeTerritory.getName());
		}
		return safeTerritory;
	}
	
	/**
	 * Checks if the game ended or not
	 * 
	 * @return true if the game ended with one army left, else false
	 */
	public boolean isWinner() {
		logger.log(Level.INFO, "");
		if(game.getPlayers().size() == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Add cards to current player based on its winning
	 * 
	 * @param currentPlayer Player current player
	 */
	public void addCard(Player currentPlayer) {
		logger.log(Level.INFO, "");
		ArrayList<Card> cards = game.getCards();
		int size = cards.size();
		int randomIndex = getRandomNumber(size);
		Card card = cards.get(randomIndex);				
		currentPlayer.getCards().add(card);
		logger.log(Level.INFO, currentPlayer.getName() + " got a card, " + card.getArmyType());
		gameInstructions.setInstructions(currentPlayer.getName() + " got a card, " + card.getArmyType() + "\n");
		cards.remove(randomIndex);
		game.setCards(cards);
	}
	
	/**
	 * Check the player has more than one number of army
	 * 
	 * @param player player who do the fortify
	 * @param selectedIndex Index of the fortify from territory
	 * @return boolean returns true if the move is valid
	 */
	public boolean validateFortifyMove(Player player, int selectedIndex) {
		logger.log(Level.INFO, "");
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
		logger.log(Level.INFO, "");
		boolean isValidNumber = false;
		int currentNumArmies = player.getOwnedTerritories().get(selectedIndex).getNumberOfArmies();
		if(currentNumArmies > fortifyNum) {
			isValidNumber = true;
		}
		return isValidNumber;
	}
	
	//Utility Functions
	/**
	 * Returns random number with a limit
	 * 
	 * @param number integer limit
	 * @return int random number
	 */
	public int getRandomNumber(int number) {
		logger.log(Level.INFO, "inside getRandomNumber function");
		int randomNumber = 0;
		Random rand = new Random();
		try {
			randomNumber = rand.nextInt(number);
		}catch(Exception e) {
			randomNumber = 0;
		}
		logger.log(Level.INFO, "inside getRandomNumber function returns" + randomNumber);
		return randomNumber;		
	}
	
	/**
	 * This compare dices method executes the comparison result of dices number
	 * 
	 * @param i the number of dices of player 1
	 * @param j the number of dices of player 2
	 * @return boolean true of false if player 1 has more dices than player 2
	 */
	public static boolean compareDices(int i, int j) {
		if(i>j) {
			return true;
		}else {
			return false;
		}
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
	 * Validates whether the player can turn in cards and if can turn in cards and add number of reinforcement armies
	 * 
	 * @param currentPlayer Player current player
	 * @return 
	 */
	public int validateCards(Player currentPlayer) {
		logger.log(Level.INFO, "Inside validateCards function");
		int numberOfArmies = 0;
		Set<String> setToReturn = new HashSet<String>();
		int size = currentPlayer.getCards().size();
		String[] cardsType = new String[size];
		boolean canTurnIn = false;
		for(int i=0;i<size;i++) {
			cardsType[i] = currentPlayer.getCards().get(i).getArmyType();
		}
		for (String param : cardsType)
		{
			setToReturn.add(param);
		}
		if(setToReturn.size() == 3 || setToReturn.size() == 1) {
			canTurnIn = true;
		}else {
			canTurnIn = false;
		}
		
		if(canTurnIn) {
			int turn = currentPlayer.getTurnInCards();
			numberOfArmies = getNumberOfArmies(turn);
			currentPlayer.setNumberOfArmies(numberOfArmies);
			//Remove cards
			if(setToReturn.size() == 3) {
				for(String param : setToReturn) {
					for(int i=0;i<currentPlayer.getCards().size();i++) {
						if(param.equals(currentPlayer.getCards().get(i).getArmyType())) {
							game.getCards().add(currentPlayer.getCards().get(i));
							currentPlayer.getCards().remove(i);
							break;
						}
					}
				}
			}else {
				for(int i=0;i<3;i++) {
					for(String param : setToReturn) {
						for(int j=0;j<currentPlayer.getCards().size();j++) {
							if(param.equals(currentPlayer.getCards().get(j).getArmyType())) {
								game.getCards().add(currentPlayer.getCards().get(j));
								currentPlayer.getCards().remove(j);
								break;
							}
						}
					}
				}
			}
			turn++;
			currentPlayer.setTurnInCards(turn);	
		}
		
		return numberOfArmies;
	}
	
	/**
	 * Get number of armies on each turns
	 * 
	 * @param turn integer 
	 * @return integer number of armies
	 */
	public int getNumberOfArmies(int turn) {
		logger.log(Level.INFO, "Inside getNumberOfArmies function");
		if(turn == 1) {
			return 4;
		}else if(turn > 1 && turn < 6) {
			return (4 + (turn -1) * 2);
		}else if(turn == 6) {
			return 15;
		}else {
			return (15 + (turn-6) * 5);
		}
	}

	/**
	 * Fortification for Human player
	 * 
	 * @param currentPlayer Player current player
	 * @param territoryFrom Territory from
	 * @param territoryTo Territory to
	 * @param fortifyNum integer number of fortification armies
	 */
	public void fortify(Player currentPlayer, Territory territoryFrom, Territory territoryTo, int fortifyNum) {
		int fromTerrNumArmies = territoryFrom.getNumberOfArmies();
		int toTerrNumArmies = territoryTo.getNumberOfArmies();
		fromTerrNumArmies = fromTerrNumArmies - fortifyNum;
		toTerrNumArmies = toTerrNumArmies + fortifyNum;
		territoryFrom.setNumberOfArmies(fromTerrNumArmies);
		territoryTo.setNumberOfArmies(toTerrNumArmies);
		currentPlayer.setFortificationStatus(true);
		currentPlayer.setPhase("REINFORCEMENT");
		gameInstructions.setInstructions(currentPlayer.getName() + " has fortified " + territoryTo.getName() + " from " + territoryFrom.getName() + " with " + fortifyNum + " armies");
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
	 * Validate Selected values of Cards JList
	 * 
	 * @param selectedValuesList list of selected values
	 * @return boolean true/if based on the selection
	 */
	public boolean validateSelectedCards(List<String> selectedValuesList) {
		Set<String> setToReturn = new HashSet<String>(); 
		for (String param : selectedValuesList)
		{
			setToReturn.add(param);
		}
		if(setToReturn.size() == 3 || setToReturn.size() == 1) {
			return true;
		}else {
			return false;
		}
	}
	
}
