package com.risk.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

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
	 * 
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

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	/**
	 * Attack 
	 * 
	 * @param attacker  
	 * @param opponent Player against whom playing
	 * @param numAttackingArmies int number of attacking armies
	 * @param numAttackedArmies int number of attacked armies
	 * @return AttackStatus statusMessage with winner
	 */
	public AttackStatus attack(int selectedIndex1, int selectedIndex2,int numAttackingArmies, int numAttackedArmies, ArrayList<Player> playerList) {
		
		/* Set up objects of Attacker */
		Player attacker = this;
		Territory attackingTerritory = this.getOwnedTerritories().get(selectedIndex1);
		int indexOfAttacker = playerList.indexOf(attacker);
		int numArmyAttacker = attacker.getNumberOfArmies();
		int numArmyAttTerr = attackingTerritory.getNumberOfArmies();
		int indexOfAttackerTerritory = attacker.getOwnedTerritories().indexOf(attackingTerritory);
		
		/* Set up objects of Opponent */
		Territory opponentTerritory = attackingTerritory.getAdjacentTerritories().get(selectedIndex2);
		Player opponent = opponentTerritory.getRuler();
		int indexOfOpponent = playerList.indexOf(opponent);
		int numArmyOpponent = opponent.getNumberOfArmies();
		int numArmyOppTerr = opponentTerritory.getNumberOfArmies();
		int indexOfOpponentTerritory = attackingTerritory.getAdjacentTerritories().indexOf(opponentTerritory);
		
		AttackStatus status = new AttackStatus();
		StringBuffer message = status.getStatusMessage();
		
		/*Logic for Attacking - Start */
		Integer[] firstDices = new Integer[numAttackingArmies];
		Integer[] secondDices = new Integer[numAttackedArmies];
		
		//Roll Dices
		for(int i=0;i<numAttackingArmies;i++) {
			firstDices[i] = new Random().nextInt(6) + 1;
		}
		
		for(int i=0;i<numAttackedArmies;i++) {
			secondDices[i] = new Random().nextInt(6) + 1;
		}
		
		Arrays.sort(firstDices, Collections.reverseOrder());
		Arrays.sort(secondDices, Collections.reverseOrder());
		
		message.append(attacker.getName() + " has "+ numAttackingArmies +" dices \n");
		System.out.println(attacker.getName() + " has "+ numAttackingArmies +" dices \n");
		for(int i=0;i<numAttackingArmies;i++) {
			message.append(firstDices[i] + "\n");
		}
		
		message.append(opponent.getName() + " has " + numAttackedArmies + " dices \n");
		System.out.println(opponent.getName() + " has " + numAttackedArmies + " dices \n");
		for(int i=0;i<numAttackedArmies;i++) {
			message.append(secondDices[i] + "\n");
		}
		
		//Comparing dices
		for(int i=0;i<numAttackedArmies;i++) {
			boolean win = compareDices(firstDices[i], secondDices[i]);
			System.out.println("Comparing " + firstDices[i] + "," + secondDices[i] + "\n");
			message.append("Comparing " + firstDices[i] + "," + secondDices[i] + "\n");
			if(win) {
				System.out.println(this.getName() + " won.\n");
				message.append(this.getName() + " won.\n");
				numAttackedArmies--;
				numArmyOpponent--;
				numArmyOppTerr--;
			}else {
				System.out.println("Inside else");
				System.out.println(opponent.getName() + " won.\n");
				message.append(opponent.getName() + " won.\n");
				numAttackingArmies--;
				numArmyAttacker--;
				numArmyAttTerr--;
			}
		}
		
		//Result
		if(numAttackingArmies == 0) {
			
			if(numArmyAttacker == 0) {
				playerList.remove(indexOfAttacker);
				attackingTerritory.setNumberOfArmies(1);
				playerList.get(indexOfOpponent).getOwnedTerritories().add(attackingTerritory);
				status.setWinner(opponent);
				status.hasWon(true);
			}else if(numArmyAttTerr == 0) {
				playerList.get(indexOfAttacker).getOwnedTerritories().remove(indexOfAttackerTerritory);
				attackingTerritory.setNumberOfArmies(1);
				playerList.get(indexOfOpponent).getOwnedTerritories().add(attackingTerritory);
				status.setWinner(opponent);
				status.hasWon(true);
			}else {
				status.setWinner(null);
				status.hasWon(false);
			}
			System.out.println(opponent.getName() + " won and balance armies " + (numAttackedArmies) + "\n");
			message.append(opponent.getName() + " won and balance armies " + (numAttackedArmies) + "\n");
		}else if(numAttackedArmies == 0) {
			if(numArmyOpponent == 0) {
				playerList.remove(indexOfOpponent);
				opponentTerritory.setNumberOfArmies(1);
				//playerList.get(indexOfAttacker).getOwnedTerritories().add(opponentTerritory);
				status.setWinner(attacker);
				status.hasWon(true);
			}else if(numArmyOppTerr == 0) {
				playerList.get(indexOfOpponent).getOwnedTerritories().remove(indexOfOpponentTerritory);
				opponentTerritory.setNumberOfArmies(1);
				//playerList.get(indexOfAttacker).getOwnedTerritories().add(opponentTerritory);
				status.setWinner(attacker);
				status.hasWon(true);
			}else {
				status.setWinner(null);
				status.hasWon(false);
			}
			System.out.println(attacker.getName() + " won and balance armies " + (numAttackingArmies) + "\n");
			message.append(attacker.getName() + " won and balance armies " + (numAttackingArmies) + "\n");
		}
		else {
			status.setWinner(null);
			status.hasWon(false);
		}
		status.setPlayerList(playerList);
		status.setStatusMessage(message);
		
		
		
		return status;
	}	
	
	public static boolean compareDices(int i, int j) {
		if(i>j) {
			return true;
		}else {
			return false;
		}
	}

}
