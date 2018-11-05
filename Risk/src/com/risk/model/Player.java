package com.risk.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import com.risk.controller.GameController;

/**
 * Model class for Player
 * 
 * @author Hareesh Kavumkulath
 * @version 1.0
 */
public class Player {
	
	@SuppressWarnings("javadoc")
	public String name;
	@SuppressWarnings("javadoc")
	public int numberOfArmies;
	@SuppressWarnings("javadoc")
	public ArrayList<Territory> ownedTerritories = new ArrayList<Territory>();
	@SuppressWarnings("javadoc")
	public ArrayList<Continent> ownedContinents = new ArrayList<Continent>();
	@SuppressWarnings("javadoc")
	public boolean isComputer;
	@SuppressWarnings("javadoc")
	public boolean fortificationStatus;
	@SuppressWarnings("javadoc")
	public String strategy;
	public ArrayList<Card> cards = new ArrayList<Card>();
	public boolean hasWon = false;
	
	/**
	 * Constructor for Player
	 * 
	 * @param name String name of the player
	 * @param isComputer boolean true or false based on whether the player is computer or human
	 * @param numberOfArmies int number of armies allocated to player
	 * @param string 
	 */
	public Player(String name, boolean isComputer, int numberOfArmies, String strategy) {
		this.name = name;
		this.isComputer = isComputer;
		this.numberOfArmies = numberOfArmies;
		this.fortificationStatus = false;
		this.strategy = strategy;
	}
	
	/**
	 *  Empty default constructor 
	 */
	public Player() {
		// TODO Auto-generated constructor stub
	}

	/** 
	 * Getter for the name of the Player
	 * 
	 * @return String name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for the name of the player
	 * 
	 * @param name String pass the name of the player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for the number of armies
	 * 
	 * @return int the number of armies
	 */
	public int getNumberOfArmies() {
		return numberOfArmies;
	}
	
	/**
	 * Setter for the number of armies
	 * 
	 * @param numberOfArmies int pass the number of armies to setter
	 */
	public void setNumberOfArmies(int numberOfArmies) {
		this.numberOfArmies = numberOfArmies;
	}
	
	/**
	 * Get the owned territories in the array list
	 * 
	 * @return ArrayList the list of the owned territories
	 */
	public ArrayList<Territory> getOwnedTerritories() {
		return ownedTerritories;
	}
	
	/**
	 * Set for the owned territories through array list
	 * 
	 * @param ownedTerritories ArrayList of owned territories
	 */
	public void setOwnedTerritories(ArrayList<Territory> ownedTerritories) {
		this.ownedTerritories = ownedTerritories;
	}
	
	/**
	 * Get the Owned Continents through array list continent
	 * 
	 * @return ArrayList list of continents
	 */
	public ArrayList<Continent> getOwnedContinents() {
		return ownedContinents;
	}
	
	/**
	 * Set for the Owned Continents through array list
	 * 
	 * @param ownedContinents ArrayList of Continents
	 */
	public void setOwnedContinents(ArrayList<Continent> ownedContinents) {
		this.ownedContinents = ownedContinents;
	}
	
	/**
	 * Returns whether the player is computer or human
	 * 
	 * @return boolean true or false if the player is computer
	 */
	public boolean isComputer() {
		return isComputer;
	}
	
	/**
	 * Sets true is the player is computer else false
	 * 
	 * @param isComputer boolean true or false 
	 */
	public void setComputer(boolean isComputer) {
		this.isComputer = isComputer;
	}
	
	/**
	 * Gets the current fortification status
	 * 
	 * @return boolean true or false of the fortification status
	 */
	public boolean isFortificationStatus() {
		return fortificationStatus;
	}
	
	/**
	 * Sets true if the player has completed fortification, else false
	 * 
	 * @param fortificationStatus boolean true or false
	 */
	public void setFortificationStatus(boolean fortificationStatus) {
		this.fortificationStatus = fortificationStatus;
	}

	/**
	 * Getter Strategy method
	 * 
	 * @return String of the strategy
	 */
	public String getStrategy() {
		return strategy;
	}

	/**
	 * Setter to set strategy
	 * 
	 * @param strategy set and define the strategy
	 */
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public boolean isHasWon() {
		return hasWon;
	}

	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
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
	public static AttackStatus attack(Territory attackerTerr, Territory opponentTerr, int numAttackerArmies,int numOpponentArmies, Game game) {
		
		StringBuffer message = new StringBuffer();
		
		AttackStatus status = new AttackStatus();
		GameController controller = new GameController();
		
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
		
		message.append(attackerTerr.getRuler().getName() + " has "+ numAttackerArmies +" dices \n");
		for(int i=0;i<numAttackerArmies;i++) {
			System.out.println(firstDices[i]);
		}
		
		message.append(opponentTerr.getRuler().getName() + " has " + numOpponentArmies + " dices \n");
		for(int i=0;i<numOpponentArmies;i++) {
			System.out.println(secondDices[i]);
		}
		
		for(int i=0;i<numOpponentArmies;i++) {
			boolean win = compareDices(firstDices[i], secondDices[i]);
			System.out.println("Comparing " + firstDices[i] + "," + secondDices[i]);
			message.append("Comparing " + firstDices[i] + "," + secondDices[i] + "\n");
			if(win) {
				message.append(opponentTerr.getRuler().getName() + " lost an army.\n");
				numArmyOpponent--;
			}else {
				message.append(attackerTerr.getRuler().getName() + " lost an army.\n");
				numArmyAttacker--;
			}
		}
		
		attackerTerr.setNumberOfArmies(numArmyAttacker);
		opponentTerr.setNumberOfArmies(numArmyOpponent);
		
		System.out.println(attackerTerr.getRuler().getName() + " has balance armies in " + attackerTerr.getName() + " is " + numArmyAttacker);
		System.out.println(opponentTerr.getRuler().getName() + " has balance armies in " + opponentTerr.getName() + " is " + numArmyOpponent);
		
		game = controller.updateGame(attackerTerr, opponentTerr, game);
		
		if(numArmyOpponent <= 0) {
			status.setHasWon(true);
			status.setWinner(attackerTerr.getRuler());
			message.append(attackerTerr.getRuler().getName() + " won and balance armies " + (numArmyAttacker) + "\n");
		}else if(numArmyAttacker <= 0) {
			status.setHasWon(true);
			status.setWinner(opponentTerr.getRuler());
			message.append(opponentTerr.getRuler().getName() + " won and balance armies " + (numArmyOpponent) + "\n");
		}else {
			status.setHasWon(false);
		}
		status.setStatusMessage(message);
		status.setGame(game);
		return status;
		
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
	 * Reinforce function
	 * 
	 * @param game 
	 * @return Player this.player
	 */
	public Player reinforce(Game game) {
		GameController controller = new GameController();
		int reinforcementArmy = controller.getNumReinforcements(this);
		int numArmiesFromContinents = controller.getNumArmiesFromContinents(this);
		reinforcementArmy = reinforcementArmy + numArmiesFromContinents;
		this.setNumberOfArmies(reinforcementArmy);
		return this;
	}

	/**
	 * Fortify with the fortifyNumber
	 * 
	 * @param territoryFrom From Territory
	 * @param territoryTo To Territory
	 * @param fortifyNum number of fortifying armies
	 * @return boolean true if fortify function is completed else false
	 */
	public boolean fortify(Territory territoryFrom, Territory territoryTo, int fortifyNum) {
		boolean status = false;
		try {
			int fromTerrNumArmies = territoryFrom.getNumberOfArmies();
			int toTerrNumArmies = territoryTo.getNumberOfArmies();
			fromTerrNumArmies = fromTerrNumArmies - fortifyNum;
			toTerrNumArmies = toTerrNumArmies + fortifyNum;
			territoryFrom.setNumberOfArmies(fromTerrNumArmies);
			territoryTo.setNumberOfArmies(toTerrNumArmies);
			this.setFortificationStatus(true);
			status = true;
		}catch(Exception e) {
			status = false;
		}
		return status;
	}
}
